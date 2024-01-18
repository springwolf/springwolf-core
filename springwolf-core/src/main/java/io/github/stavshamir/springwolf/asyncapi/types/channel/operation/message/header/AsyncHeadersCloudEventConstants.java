// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header;

public class AsyncHeadersCloudEventConstants {
    public static final String CONTENT_TYPE = "content-type";
    public static final String CONTENT_TYPE_DESC = "CloudEvent Content-Type Header";
    public static final String ID = "ce_id";
    public static final String ID_DESC = "CloudEvent Id Header";
    public static final String SPECVERSION = "ce_specversion";
    public static final String SPECVERSION_DESC = "CloudEvent Spec Version Header";
    public static final String SOURCE = "ce_source";
    public static final String SOURCE_DESC = "CloudEvent Source Header";
    public static final String SUBJECT = "ce_subject";
    public static final String SUBJECT_DESC = "CloudEvent Subject Header";
    public static final String TIME = "ce_time";
    public static final String TIME_DESC = "CloudEvent Time Header";
    public static final String TYPE = "ce_type";
    public static final String TYPE_DESC = "CloudEvent Payload Type Header";

    private AsyncHeadersCloudEventConstants() {}
}
