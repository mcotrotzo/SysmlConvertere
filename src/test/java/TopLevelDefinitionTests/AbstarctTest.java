package TopLevelDefinitionTests;

import org.example.MapperService;
import org.example.Mapping.Interfaces.Base.Model;
import org.example.Mapping.Interfaces.Base.TypeKind.TypeKindNamespace;
import org.example.Mapping.NewVersion.MappingException;
import org.example.TwinDataBase;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

	protected <P extends Model<Z>, Z extends TypeKindNamespace> void assertParent(Model<?> child, Class<P> parentType, Class<Z> parentKind, String parentName) {
		P expectedParent = named(parentType, parentKind, parentName);
		Model<?> actualParent = child.getParent().orElseThrow(() -> new AssertionError(child.getName() + " has no parent"));
		assertEquals(expectedParent.getId(), actualParent.getId());
	}

	protected <T extends Model<Z>, Z extends TypeKindNamespace> void assertAmount(Class<T> type, Class<Z> typeKind, int expected) {
		assertEquals(expected, result.get(type, typeKind).size());
	}

	protected <T extends Model<Z>, Z extends TypeKindNamespace> T named(Class<T> type, Class<Z> typeKind, String name) {
		var matches = result.get(type, typeKind).stream().filter(element -> name.equals(element.getName())).toList();

		if (matches.isEmpty()) {
			throw new AssertionError(type.getSimpleName() + " not found: " + name);
		}

		if (matches.size() > 1) {
			throw new AssertionError("Expected exactly one %s named '%s', but found %d.".formatted(type.getSimpleName(), name, matches.size()));
		}

		return matches.getFirst();
	}
}