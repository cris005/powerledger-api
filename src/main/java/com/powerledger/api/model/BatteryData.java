/*
 * Copyright 2016 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file except in compliance
 * with the License. A copy of the License is located at
 *
 * http://aws.amazon.com/apache2.0/
 *
 * or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES
 * OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package com.powerledger.api.model;


import java.util.*;
import java.util.concurrent.ThreadLocalRandom;


public class BatteryData {
    private static final List<String> names = new ArrayList<>();
    static {
        names.add("Cannington");
        names.add("Midland");
        names.add("Hay Street");
        names.add("Mount Adams");
        names.add("Koolan Island");
    }

    private static final List<String> postcodes = new ArrayList<>();
    static {
        postcodes.add("6107");
        postcodes.add("6057");
        postcodes.add("6000");
        postcodes.add("6525");
        postcodes.add("6733");
    }

    public static List<String> getNames() {
        return names;
    }

    public static List<String> getPostcodes() {
        return postcodes;
    }

    public static String getRandomName() {
        return names.get(ThreadLocalRandom.current().nextInt(0, names.size() - 1));
    }

    public static String getRandomPostcode() {
        return postcodes.get(ThreadLocalRandom.current().nextInt(0, postcodes.size() - 1));
    }

    public static Integer getRandomCapacity() {
        return (int) ((Math.random() * (10000 - 85000)) + 85000);
    }
}
