// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.example.kafka.protobuf.model;

import lombok.Data;

/* This class is a Command object and could be used to transform incoming request body into a Java object
 */
@Data
public class MessageRequest {

    private String firstName;
    private String lastName;
}
