// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.components.postprocessors;

class AvroBeta {

    public String getName() {
        return "beta";
    }

    public org.apache.avro.specific.SpecificData getSpecificData() {
        return new org.apache.avro.specific.SpecificData();
    }

    public org.apache.avro.Schema getSchema() {
        return null;
    }
}
