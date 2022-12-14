package com.senla.bankApp.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class FileManager {

    @Value("${storage.path}")
    private String path;

    public void writeInfo(Map<String, String> storage) {

        try (Stream<String> lines = Files.lines(Paths.get(path), StandardCharsets.UTF_8)) {

            List<String> list = new ArrayList<>();

            for (Map.Entry<String, String> me : storage.entrySet()) {
                String stringBuilder = me.getKey() + " " +
                        me.getValue();
                list.add(stringBuilder);
            }

            Files.write(Paths.get(path), list, StandardCharsets.UTF_8);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String, String> readFile() {

        Map<String, String> storage = new HashMap<>();

        try (Stream<String> lines = Files.lines(Paths.get(path), StandardCharsets.UTF_8)) {

            storage = lines.collect(Collectors.
                    toMap(x -> x.substring(0, x.indexOf(' ')), x -> x.substring(x.indexOf(' ') + 1, x.length())));

        } catch (IOException e) {
            e.printStackTrace();
        }

        return storage;
    }
}