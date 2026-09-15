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

	private static final String DEFAULT_CONTENT_DIRECTORY = "src/test/java/TopLevelDefinitionTests/";

	protected TwinDataBase result;


	@BeforeEach
	public void testTopLevelDefinition() throws IOException, MappingException {
		String contentDirectory = createContentDirectoryOrGetPath(getContent());
		MapperService mapperService = new MapperService(contentDirectory);

		result = mapperService.map();
	}

	public Optional<String> getContent() {
		return Optional.of(DEFAULT_CONTENT_DIRECTORY);
	}

	private String createContentDirectoryOrGetPath(Optional<String> value) throws IOException {
		if (value.isEmpty()) {
			return DEFAULT_CONTENT_DIRECTORY;
		}

		String supplied = value.get();

		Path existingPath = tryExistingDirectory(supplied);
		if (existingPath != null) {
			return existingPath.toString();
		}

		Path tempDirectory = Files.createTempDirectory("twin-content-");
		Path sysmlFile = tempDirectory.resolve("Content.sysml");
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