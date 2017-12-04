package vladyagl.regexp

import org.graphstream.graph.Edge
import org.graphstream.graph.Graph
import org.graphstream.graph.Node
import org.graphstream.graph.implementations.SingleGraph

class Visualizator {

    val levels: ArrayList<Int> = ArrayList()
    val gap = 300
    var leftSide = 0

    private fun Graph.addTree(tree: Parser.Node, parent: Parser.Node? = null, level: Int = 0): Pair<Int, Int> {
        this.addNode<Node>("" + tree.hashCode())
        val node = this.getNode<Node>("" + tree.hashCode())
        node.addAttribute("ui.label", tree.name)
        if (parent != null) {
            this.addEdge<Edge>("" + tree.hashCode() + parent.hashCode(), "" + tree.hashCode(), "" + parent.hashCode())
        }

        val min = maxOf(if (levels.size > level)
            levels[level] + 1
        else {
            levels.add(0)
            0
        }, leftSide + 1)
        var max = min
        tree.children.forEach {
            val (_, right) = this.addTree(it, tree, level + 1)
            max = maxOf(right, max)
        }
        val x = (min + max) / 2
        levels[level] = maxOf(x, levels[level])
        node.setAttribute("xy", x * gap, level * -gap + gap * 10 / (level + 1))
        if (level > 0) levels[level - 1] = maxOf(levels[level], levels[level - 1])

        leftSide = maxOf(leftSide, x)
        return Pair(min, max)
    }


    fun visualize(tree: Parser.Node) {
        val graph: Graph = SingleGraph("Tree")

        graph.addTree(tree)

        graph.removeAttribute("ui.hide")
        graph.addAttribute("ui.stylesheet", "node { text-size: 20; }")

        val viewer = graph.display()
//        viewer.enableAutoLayout()
        viewer.disableAutoLayout()
    }
}