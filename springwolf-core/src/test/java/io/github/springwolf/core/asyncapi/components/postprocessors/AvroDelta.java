// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.components.postprocessors;

class AvroDelta {

    public String getName() {
        return "delta";
    }

    public org.apache.avro.specific.SpecificData getSpecificData() {
        return new org.apache.avro.specific.SpecificData();
    }

    public org.apache.avro.Schema getSchema() {
        return null;
    }
}
