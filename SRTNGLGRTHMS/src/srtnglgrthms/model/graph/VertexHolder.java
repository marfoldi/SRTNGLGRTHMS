package srtnglgrthms.model.graph;

import javafx.scene.Group;

public class VertexHolder extends Group{
	public VertexHolder(final Vertex vertex) {
		super(vertex.getGraphics());
	}
}
