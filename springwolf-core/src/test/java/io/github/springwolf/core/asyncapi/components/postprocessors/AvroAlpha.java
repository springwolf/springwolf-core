// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.components.postprocessors;

import java.util.List;

class AvroAlpha {

    public String getName() {
        return "alpha";
    }

    public List<AvroBeta> getAvroBetas() {
        return List.of(new AvroBeta());
    }

    public org.apache.avro.specific.SpecificData getSpecificData() {
        return new org.apache.avro.specific.SpecificData();
    }

    public org.apache.avro.Schema getSchema() {
        return null;
    }
}
