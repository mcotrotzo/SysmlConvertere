package TopLevelDefinitionTests;

import org.example.MapperService;
import org.example.Mapping.Model.Attribute.TwinAttributeUsage;
import org.example.Mapping.Model.AbstractModel;
import org.example.TwinDataBase;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class AbstarctTest {

	private static final String DEFAULT_CONTENT_DIRECTORY = "src/test/java/TopLevelDefinitionTests/";

	protected TwinDataBase result;


	@BeforeEach
	public void testTopLevelDefinition() throws IOException, IllegalArgumentException {
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

	protected <T extends AbstractModel<?>> List<T> originals(Class<T> type) {
		return result.getByType(type).stream().filter(element -> !element.isInherited()).toList();
	}

	protected <P extends AbstractModel<?>> void assertParent(AbstractModel<?> child, Class<P> parentType, String parentName) {
		P expectedParent = named(parentType, parentName);
		AbstractModel<?> actualParent = child.getParent().orElseThrow(() -> new AssertionError(child.getName() + " has no parent"));
		assertEquals(expectedParent.getId(), actualParent.getId());
	}

	protected <T extends AbstractModel<?>> void assertAmount(Class<T> type, int expected) {
		assertEquals(expected, originals(type).size());
	}

	protected <T extends AbstractModel<?>> T named(Class<T> type, String name) {
		var matches = originals(type).stream().filter(element -> name.equals(element.getName())).toList();

		if (matches.isEmpty()) {
			throw new AssertionError(type.getSimpleName() + " not found: " + name);
		}

		if (matches.size() > 1) {
			throw new AssertionError("Expected exactly one %s named '%s', but found %d.".formatted(type.getSimpleName(), name, matches.size()));
		}

		return matches.getFirst();
	}

	protected List<AbstractModel<?>> attributeChain(AbstractModel<?> target) {
		List<AbstractModel<?>> chain = new ArrayList<>();
		AbstractModel<?> current = target;
		while (current instanceof TwinAttributeUsage<?, ?>) {
			chain.addFirst(current);
			current = current.getParent().orElse(null);
		}
		return chain;
	}
}
