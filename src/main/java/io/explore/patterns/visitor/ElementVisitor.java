package io.explore.patterns.visitor;

public class ElementVisitor implements Visitor {
    @Override
    public void visit(XmlElement xe) {
        System.out.println("Visiting XmlElement with uuid: " + xe.uuid);
    }

    @Override
    public void visit(JsonElement je) {
        System.out.println("Visiting JsonElement with uuid: " + je.uuid);
    }
}
