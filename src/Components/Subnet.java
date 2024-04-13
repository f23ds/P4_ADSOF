package Components;

import Interfaces.*;
import java.util.*;

/**
 * Clase para dar soporte a subredes
 *
 * @author Fabio Desio Alba López
 */
public class Subnet extends NetworkElement {

  private ArrayList<Node> nodes;

  /**
   * Constructor para la subred
   * @param nodes
   */
  public Subnet(Node... nodeList) {
    this.nodes = new ArrayList<Node>();
    for (Node node : nodeList) {
      node.setParent(this);
      this.nodes.add(node);
    }
    this.parent = null;
  }

  @Override
  public String fullName() {
    return String.format("Subnet#%03d", this.id);
  }

  /* ----------------------------- MÉTODOS DE ICONNECTABLE ----------------------------- */

  @Override
  public void broadcast(IMessage msg) {
    msg.process(this);
    nodes.stream().forEach(n -> n.broadcast(msg));
  }

  public boolean isSubnet() {
    return true;
  }

  public boolean isNode() {
    return false;
  }

  public Node getNode() {
    return null;
  }

  public Subnet getSubnet() {
    return this;
  }

  /* ----------------------------- MÉTODOS DE IMESSAGE ----------------------------- */


  @Override
  public String toString() {
    String subNetStr = String.format(
      "Node network of %d nodes: [",
      nodes.size()
    );

    for (Node node : nodes) {
      subNetStr += node.toString();
    }

    subNetStr = subNetStr.substring(0, subNetStr.length()) + "]";

    return subNetStr;
  }

  /* GETTERS AND SETTERS */
  public int getId() {
    return id;
  }

  public ArrayList<Node> getNodes() {
    return nodes;
  }

  public void setNodes(ArrayList<Node> nodes) {
    this.nodes = nodes;
  }
}
