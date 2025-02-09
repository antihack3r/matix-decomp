// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.util.file;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import java.lang.reflect.Type;
import java.util.Scanner;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import com.google.gson.Gson;

public class FileService
{
    private static final Gson gson;
    
    public static void setFileContents(final File file, final String fileContents) throws IOException {
        if (!file.exists()) {
            file.createNewFile();
        }
        final PrintWriter printWriter = new PrintWriter(file);
        printWriter.print(fileContents);
        printWriter.close();
    }
    
    public static void setFileContentsAsJson(final File file, final Object object) throws IOException {
        final String json = FileService.gson.toJson(object);
        setFileContents(file, json);
    }
    
    public static String getFileContents(final File file) throws IOException {
        final Scanner scanner = new Scanner(file);
        final StringBuilder stringBuilder = new StringBuilder();
        while (scanner.hasNextLine()) {
            stringBuilder.append(scanner.nextLine());
        }
        scanner.close();
        return stringBuilder.toString();
    }
    
    public static <T> T getFileContents(final File file, final Type type) throws IOException, JsonSyntaxException {
        final String fileContents = getFileContents(file);
        return (T)FileService.gson.fromJson(fileContents, type);
    }
    
    public static <T> T getFileContents(final File file, final Class<T> type) throws IOException, JsonSyntaxException {
        return getFileContents(file, (Type)type);
    }
    
    static {
        gson = new GsonBuilder().setPrettyPrinting().create();
    }
}
