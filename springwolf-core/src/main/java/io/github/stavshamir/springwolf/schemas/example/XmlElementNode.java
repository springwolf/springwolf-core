package io.github.stavshamir.springwolf.schemas.example;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.w3c.dom.Element;

@ToString
@RequiredArgsConstructor
public class XmlElementNode implements XmlNode<Element>{

    private final Element value;


    @Override
    public Element getValue() {
        return value;
    }
}
