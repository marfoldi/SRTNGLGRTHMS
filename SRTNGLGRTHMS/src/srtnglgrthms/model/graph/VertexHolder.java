package srtnglgrthms.model.graph;

import javafx.scene.Group;

/**
 * 
 * @author <a href="mailto:marfoldi@caesar.elte.hu">M�rf�ldi P�ter Bence</a>
 */
public class VertexHolder extends Group {
	public VertexHolder(final Vertex vertex) {
		super(vertex.getGraphics());
	}
}
