package TopLevelDefinitionTests;

import org.example.MapperService;
import org.example.Mapping.Interfaces.Base.Model;
import org.example.Mapping.NewVersion.MappingException;
import org.example.TwinDataBase;
import org.junit.jupiter.api.BeforeEach;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstarctTest {

	private static final String DEFAULT_TEST_MODEL_DIRECTORY = "src/test/java/TopLevelDefinitionTests/TwinModelSysml/";

	private static final String DEFAULT_USER_LIBRARY_DIRECTORY = "src/test/java/TopLevelDefinitionTests/UserDefinedLibrary/";

	protected TwinDataBase result;


	@BeforeEach
	public void testTopLevelDefinition() throws IOException, MappingException {
		String testModelDirectory = createModelDirectoryOrGetPath(getTestModel());

		String userLibraryDirectory = createUserLibraryDirectoryOrGetPath(getUserLibrary());
		MapperService mapperService = new MapperService(testModelDirectory, userLibraryDirectory);

		result = mapperService.map();
	}

	public Optional<String> getTestModel() {
		return Optional.of(DEFAULT_TEST_MODEL_DIRECTORY);
	}


	public Optional<String> getUserLibrary() {
		return Optional.of(DEFAULT_USER_LIBRARY_DIRECTORY);
	}

	public String createModelDirectoryOrGetPath(Optional<String> value) throws IOException {

		return createDirectoryOrGetPath(value, DEFAULT_TEST_MODEL_DIRECTORY, "test-model-", "TestModel.sysml");
	}

	public String createUserLibraryDirectoryOrGetPath(Optional<String> value) throws IOException {

		return createDirectoryOrGetPath(value, DEFAULT_USER_LIBRARY_DIRECTORY, "user-library-", "UserLibrary.sysml");
	}

	private String createDirectoryOrGetPath(Optional<String> value, String defaultDirectory, String tempDirectoryPrefix, String fileName) throws IOException {

		if (value.isEmpty()) {
			return defaultDirectory;
		}

		String supplied = value.get();

		Path existingPath = tryExistingDirectory(supplied);

		if (existingPath != null) {
			return existingPath.toString();
		}

		Path tempDirectory = Files.createTempDirectory(tempDirectoryPrefix);

		Path sysmlFile = tempDirectory.resolve(fileName);

		Files.writeString(sysmlFile, supplied);

		sysmlFile.toFile().deleteOnExit();
		tempDirectory.toFile().deleteOnExit();

		return tempDirectory.toString();
	}

	private Path tryExistingDirectory(String supplied) {
		try {
			Path path = Path.of(supplied);

			if (!Files.exists(path)) {
				return null;
			}

			if (!Files.isDirectory(path)) {
				throw new IllegalArgumentException("Expected directory, but found file: " + path);
			}

			return path;
		} catch (InvalidPathException ignored) {
			return null;
		}
	}

	protected void assertParent(Model child, Class<? extends Model> parentType, String parentName) {
		Model expectedParent = named(parentType, parentName);

		Model actualParent = child.getParent().orElseThrow(() -> new AssertionError(child.getName() + " has no parent"));

		assertEquals(expectedParent.getId(), actualParent.getId());
	}

	protected void assertAmount(Class<? extends Model> type, int expected) {
		assertEquals(expected, result.get(type).size());
	}

	protected <T extends Model> T named(Class<T> type, String name) {
		return result.get(type).stream().filter(element -> name.equals(element.getName())).findFirst().orElseThrow(() -> new AssertionError(type.getSimpleName() + " not found: " + name));
	}
}