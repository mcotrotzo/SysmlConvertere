package org.example;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.diagnostics.Severity;
import org.omg.sysml.interactive.SysMLInteractive;
import org.omg.sysml.interactive.SysMLInteractiveResult;
import org.omg.sysml.lang.sysml.Element;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class ReadManager {

	private static final Path STANDARD_LIBRARY;

	static {
		try {
			STANDARD_LIBRARY = extractStandardLibrary("sysml_library");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private static final Path DT_LIBRARY;

	static {
		try {
			DT_LIBRARY = extractStandardLibrary("DTLibrary");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}


	private final SysMLInteractive sysMLInteractive;

	private final SysMLInteractiveResult dtLibraryResult;
	private final SysMLInteractiveResult userLibraryResult;
	private final SysMLInteractiveResult modelResult;

	private final Element rootElement;
	private final LoadedResources loadedResources;


	private static Path extractStandardLibrary(String ressourceName) throws IOException {

		Path tempDirectory = Files.createTempDirectory(ressourceName+"_temp");

		try (var input = ReadManager.class
				.getClassLoader()
				.getResourceAsStream(ressourceName+".zip")) {

			if (input == null) {
				throw new IllegalStateException(
						"Bundled SysML standard library not found."
				);
			}

			try (var zip = new java.util.zip.ZipInputStream(input)) {

				java.util.zip.ZipEntry entry;

				while ((entry = zip.getNextEntry()) != null) {

					Path destination = tempDirectory
							.resolve(entry.getName())
							.normalize();

					if (!destination.startsWith(tempDirectory)) {
						throw new IOException(
								"Invalid ZIP entry: " + entry.getName()
						);
					}

					if (entry.isDirectory()) {
						Files.createDirectories(destination);
					} else {
						Files.createDirectories(destination.getParent());
						Files.copy(
								zip,
								destination,
								java.nio.file.StandardCopyOption.REPLACE_EXISTING
						);
					}

					zip.closeEntry();
				}
			}
		}

		return tempDirectory;
	}

	public ReadManager(String userTwinModelPath, String userLibraryPath) {
		try {
			Path standardLibrary = requireDirectory(STANDARD_LIBRARY, "Standard library");

			Path dtLibrary = requireDirectory(DT_LIBRARY, "DT library");

			Path userLibrary = requireDirectory(Path.of(userLibraryPath), "User library");

			Path twinModels = requireDirectory(Path.of(userTwinModelPath), "Twin model directory");

			sysMLInteractive = SysMLInteractive.createInstance();


			Set<Resource> resourcesBeforeStandardLibrary = snapshotResources();

			sysMLInteractive.loadLibrary(standardLibrary.toString());

			Set<Resource> standardLibraryResources = snapshotResources();

			standardLibraryResources.removeAll(resourcesBeforeStandardLibrary);

			dtLibraryResult = processDirectory(dtLibrary, "DT library");

			Resource dtLibraryResource = requireResultResource(dtLibraryResult, "DT library");

			userLibraryResult = processDirectory(userLibrary, "User library");

			Resource userLibraryResource = requireResultResource(userLibraryResult, "User library");

			modelResult = processDirectory(twinModels, "Twin model directory");

			Resource modelResource = requireResultResource(modelResult, "Twin model directory");

			rootElement = Objects.requireNonNull(modelResult.getRootElement(), "No root element was produced by the twin model.");

			loadedResources = new LoadedResources(standardLibraryResources, dtLibraryResource, userLibraryResource, modelResource, rootElement);

		} catch (IOException exception) {
			throw new RuntimeException("Failed to initialize ReadManager.", exception);
		}
	}

	private static Resource requireResultResource(SysMLInteractiveResult result, String description) {
		Element root = result.getRootElement();

		if (root == null) {
			throw new IllegalStateException(description + " produced no root element.");
		}

		Resource resource = root.eResource();

		if (resource == null) {
			throw new IllegalStateException(description + " root element has no EMF resource.");
		}

		return resource;
	}

	private static String readAllSysml(Path directory) throws IOException {

		List<Path> files;

		try (Stream<Path> paths = Files.walk(directory)) {
			files = paths.filter(Files::isRegularFile).filter(ReadManager::isSysmlFile).map(path -> path.toAbsolutePath().normalize()).distinct().sorted(Comparator.comparing(Path::toString)).toList();
		}

		if (files.isEmpty()) {
			return "";
		}

		try {
			return files.stream().map(ReadManager::readFile).collect(Collectors.joining(System.lineSeparator() + System.lineSeparator()));
		} catch (UncheckedIOException exception) {
			throw exception.getCause();
		}
	}

	private static String readFile(Path file) {
		try {
			return "// SOURCE: " + file.toAbsolutePath().normalize() + System.lineSeparator() + Files.readString(file) + System.lineSeparator();

		} catch (IOException exception) {
			throw new UncheckedIOException("Failed to read SysML file: " + file.toAbsolutePath(), exception);
		}
	}

	private static void printIssues(SysMLInteractiveResult result, String description) {
		result.getIssues().forEach(issue -> System.err.printf("%s while processing %s at %d:%d: %s%n", issue.getSeverity(), description, issue.getLineNumber(), issue.getColumn(), issue.getMessage()));
	}

	private static void failOnErrors(SysMLInteractiveResult result, String description) {
		boolean hasErrors = result.getIssues().stream().anyMatch(issue -> issue.getSeverity() == Severity.ERROR);

		if (hasErrors) {
			throw new IllegalStateException("SysML errors while processing " + description);
		}
	}

	private static boolean isSysmlFile(Path path) {
		String fileName = path.getFileName().toString().toLowerCase(Locale.ROOT);

		return fileName.endsWith(".sysml");
	}

	private static Path requireDirectory(Path path, String description) {
		Objects.requireNonNull(path, "path");

		Path normalized = path.toAbsolutePath().normalize();

		if (!Files.exists(normalized)) {
			throw new IllegalArgumentException(description + " does not exist: " + normalized);
		}

		if (!Files.isDirectory(normalized)) {
			throw new IllegalArgumentException(description + " is not a directory: " + normalized);
		}

		return normalized;
	}

	private SysMLInteractiveResult processDirectory(Path directory, String description) throws IOException {

		String content = readAllSysml(directory);

		if (content.isBlank()) {
			throw new IllegalArgumentException("No .sysml files found in " + description + ": " + directory);
		}

		SysMLInteractiveResult result = sysMLInteractive.process(content, true);

		printIssues(result, description);
		failOnErrors(result, description);

		return result;
	}

	private Set<Resource> snapshotResources() {
		return new HashSet<>(sysMLInteractive.getResourceSet().getResources());
	}

	public SysMLInteractive getSysMLInteractive() {
		return sysMLInteractive;
	}

	public SysMLInteractiveResult getDtLibraryResult() {
		return dtLibraryResult;
	}

	public SysMLInteractiveResult getUserLibraryResult() {
		return userLibraryResult;
	}

	public SysMLInteractiveResult getModelResult() {
		return modelResult;
	}

	public Element getRootElement() {
		return rootElement;
	}

	public LoadedResources getLoadedResources() {
		return loadedResources;
	}
}