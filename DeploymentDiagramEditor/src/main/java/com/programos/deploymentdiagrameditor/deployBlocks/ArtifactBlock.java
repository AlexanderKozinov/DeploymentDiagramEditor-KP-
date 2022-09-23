package com.programos.deploymentdiagrameditor.deployBlocks;

import com.programos.deploymentdiagrameditor.Dragger;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

/**
 * Class for artifacts
 */
public class ArtifactBlock extends DefaultBlock{
    public ArtifactBlock(DeployBlockType type, Point2D point, String text, Dragger onDrag,Stereotype sType) {
        super(type, point, text, onDrag, sType);
    }
    public ArtifactBlock(DeployBlockType type, Point2D point, String text,Stereotype sType) {
        super(type, point, text,sType);
    }

    /**
     * @return copy
     */
    @Override
    public DefaultBlock copy(){
        return new ArtifactBlock(this.type, new Point2D(this.pX,this.pY), this.text, this.listeners.get(this.listeners.size()-1),this.sType);
    }
    @Override
    public DefaultBlock copy(Dragger dragger){
        return new ArtifactBlock(this.type, new Point2D(this.pX,this.pY), this.text, dragger,this.sType);
    }

    /**
     * draw a shapes
     */
    @Override
    public void draw()
    {
        showText.applyCss();
        showText.layout();
        editableText.layout();
        editableText.applyCss();
        getChildren().clear();
        float offset = 10f;

        var textWidth = showText.prefWidth(-1);
        var textHeight = showText.prefHeight(-1);

        Rectangle body = new Rectangle(
                10,
                0,
                textWidth + offset,
                Math.max(textHeight + offset,30));
        body.setStroke(Color.BLACK);
        body.setFill(Color.WHITE);
        Rectangle part1 = new Rectangle(
                0,
                body.getHeight()*0.15,
                15,
                body.getHeight()*0.27);
        part1.setFill(Color.WHITE);
        part1.setStroke(Color.BLACK);
        Rectangle part2 = new Rectangle(
                0,
                body.getHeight()*0.6,
                15,
                body.getHeight()*0.27);
        point = new Point2D(getTranslateX(), getTranslateY());
        width = body.getWidth()+10;
        height = body.getHeight();
        part2.setStroke(Color.BLACK);
        part2.setFill(Color.WHITE);
        getChildren().add(body);
        getChildren().add(part1);
        getChildren().add(part2);
        getChildren().add(showText);
        getChildren().add(editableText);
        getChildren().add(switchTypes(body));
        showText.setTranslateX(offset * 1.5f);
        showText.setTranslateY(offset * 0.5f);
    }
    public Rectangle switchTypes(Rectangle body)
    {
        Rectangle icon=new Rectangle(body.getWidth()-5,0,10,10);
        switch (sType) {
            case CD -> icon.setFill(new ImagePattern(new Image("Disc.png")));
            case COMPUTER -> icon.setFill(new ImagePattern(new Image("computer.png")));
            case FLOPPY_DISK -> icon.setFill(new ImagePattern(new Image("floppydisk.png")));
            case SERVER -> icon.setFill(new ImagePattern(new Image("server.png")));
            case DOCUMENT -> icon.setFill(new ImagePattern(new Image("doc.png")));
            case EMPTY -> icon.setFill(Color.TRANSPARENT);
        }
        return icon;
    }
}
