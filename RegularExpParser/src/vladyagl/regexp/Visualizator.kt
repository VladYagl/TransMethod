package vladyagl.regexp

import org.graphstream.graph.Edge
import org.graphstream.graph.Graph
import org.graphstream.graph.Node
import org.graphstream.graph.implementations.SingleGraph

class Visualizator {

    private fun Graph.addTree(tree: Parser.Node, parent: Parser.Node? = null) {
        this.addNode<Node>("" + tree.hashCode())
        val node = this.getNode<Node>("" + tree.hashCode())
        node.addAttribute("ui.label", tree.name)
        if (parent != null) {
            this.addEdge<Edge>("" + tree.hashCode() + parent.hashCode(), "" + tree.hashCode(), "" + parent.hashCode())
        }
        tree.children.forEach {
            this.addTree(it, tree)
        }
    }

    fun visualize(tree: Parser.Node) {
        val graph: Graph = SingleGraph("Tutorial 1")

        graph.addTree(tree)

        graph.removeAttribute( "ui.hide" );

        val viewer = graph.display()
        viewer.enableAutoLayout()
    }
}