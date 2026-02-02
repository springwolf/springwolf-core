// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.components.postprocessors;

import java.util.List;

class AvroGamma {

    public String getName() {
        return "gamma";
    }

    public List<AvroDelta> getAvroDeltas() {
        return List.of(new AvroDelta());
    }

    public org.apache.avro.specific.SpecificData getSpecificData() {
        return new org.apache.avro.specific.SpecificData();
    }

    public org.apache.avro.Schema getSchema() {
        return null;
    }
}
