import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Wall implements Structure{
    public List<Block> blocks;

    @Override
    public Optional<Block> findBlockByColor(String color) {
        return ChangeCompositeBlocksIntoBlocks(blocks.stream()).filter(b -> b.getColor().equals(color)).findAny();
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        return ChangeCompositeBlocksIntoBlocks(blocks.stream()).filter(b -> b.getMaterial().equals(material)).collect(Collectors.toList());
    }

    @Override
    public int count() {
        return (int) ChangeCompositeBlocksIntoBlocks(blocks.stream()).count();
    }

    private Stream<Block> ChangeCompositeBlocksIntoBlocks(Stream<Block> blocksStream){
        return blocksStream.flatMap(block -> block instanceof CompositeBlock?((CompositeBlock) block).getBlocks().stream():Stream.of(block));
    }
}
