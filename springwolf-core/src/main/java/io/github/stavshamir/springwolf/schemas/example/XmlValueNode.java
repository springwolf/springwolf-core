package io.github.stavshamir.springwolf.schemas.example;

import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@RequiredArgsConstructor
public class XmlValueNode implements XmlNode<String> {

    private final String value;

    @Override
    public String getValue() {
        return value;
    }
}
