package ch07_managing_files_and_directories;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

/**
 * Created by sofia on 2/20/17.
 */
public class FilesAndDirectories {

    static final String URI = "/Users/sofia/Projects/workspace/intellij/all-coding-problems/3-course-problems/java-advanced-training/src/ch07_managing_files_and_directories/files/";

    public static void main(String[] args) throws IOException {

        /******************************************************
         * Path class
         ******************************************************/
        System.out.println("=== Path class ===");

        Path path = Paths.get(URI + "loremipsum.txt");
        System.out.println(path.toString());
        System.out.println(path.getFileName());
        System.out.println(path.getNameCount());
        System.out.println(path.getName(path.getNameCount()-1));

        try {
            Path realPath = path.toRealPath(LinkOption.NOFOLLOW_LINKS);
            System.out.println(realPath);
        } catch (NoSuchFileException e) {

        }

        Path absPath = path.toAbsolutePath();
        System.out.println(absPath);

        System.out.println();


        /******************************************************
         * managing files and directories
         ******************************************************/
        System.out.println("=== Managing Files and Directories ===");

        Path source = Paths.get(URI + "loremipsum.txt");
        System.out.println(source.getFileName());

        Path target = Paths.get(URI + "newfile.txt");
        try {
            Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
        } catch (NoSuchFileException e) {

        }
        System.out.println("File copied");


        Path toDelete = Paths.get(URI + "todelete.txt");
        try {
            Files.delete(toDelete);
        } catch (NoSuchFileException e) {

        }
        System.out.println("File deleted");

        Path newdir = Paths.get(URI + "newdir");
        Files.createDirectories(newdir);
        try {
            Files.move(source, newdir.resolve(source.getFileName()), StandardCopyOption.REPLACE_EXISTING);
        } catch (NoSuchFileException e) {

        }
        System.out.println("File moved");

        System.out.println();


        /******************************************************
         * reading and writing text files
         ******************************************************/
        System.out.println("=== Reading and Writing Files ===");

        source = Paths.get(URI + "newdir/loremipsum.txt");
        System.out.println(source.getFileName());

        target = Paths.get(URI + "newfile.txt");
        System.out.println(target.getFileName());

        Charset charset = Charset.forName("US-ASCII");
        List<String> lines = new ArrayList<>();

        try (BufferedReader br = Files.newBufferedReader(source, charset)) {
            String line = null;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try (BufferedWriter bw = Files.newBufferedWriter(target, charset)) {
            Iterator<String> itr = lines.iterator();
            while (itr.hasNext()) {
                String s = itr.next();
                bw.append(s, 0, s.length());
                bw.newLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println();


        /******************************************************
         * walking file tree
         ******************************************************/
        System.out.println("=== Walking File Tree ===");

        Path fileDir = Paths.get(URI);
        MyFileVisitor visitor = new MyFileVisitor();
        Files.walkFileTree(fileDir, visitor);

        System.out.println();


        /******************************************************
         * finding files
         ******************************************************/
        System.out.println("=== Finding Files ===");

        fileDir = Paths.get(URI);
        MyFileFinder finder = new MyFileFinder("file*.txt");
        Files.walkFileTree(fileDir, finder);

        List<Path> foundFiles = finder.foundPaths;

        if (foundFiles.size() > 0) {
            for (Path foundFile : foundFiles) {
                System.out.println("Found: "+foundFile.getFileName());
            }
        } else {
            System.out.println("No files were found!");
        }

        System.out.println();


        /******************************************************
         * watching a directory
         ******************************************************/
        System.out.println("=== Watching a Directory ===");

        try (WatchService watcher = FileSystems.getDefault().newWatchService()) {
            Map<WatchKey, Path> keyMap = new HashMap<>();
            Path watchedPath = Paths.get(URI);

            keyMap.put(watchedPath.register(watcher,
                    StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_DELETE,
                    StandardWatchEventKinds.ENTRY_MODIFY),
                    watchedPath);

            WatchKey watchKey;

            do {
                watchKey = watcher.take();
                Path eventDir = keyMap.get(watchKey);

                for (WatchEvent<?> event : watchKey.pollEvents()) {
                    WatchEvent.Kind<?> kind = event.kind();
                    Path eventPath = (Path) event.context();
                    System.out.println(eventDir.getFileName() + ": " + kind + ": " + eventPath);
                }
            } while (watchKey.reset());

        } catch (Exception e) {

        }


    }

    public static class MyFileVisitor extends SimpleFileVisitor<Path> {

        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            System.out.println("About to visit "+dir.getFileName());
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            if (attrs.isRegularFile()) {
                System.out.print("Regular File: ");
            }
            System.out.println(file.getFileName());
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
            System.err.println(exc.getMessage());
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
            System.out.println("Just visited "+dir.getFileName());
            return FileVisitResult.CONTINUE;
        }
    }

    public static class MyFileFinder extends SimpleFileVisitor<Path> {

        private PathMatcher matcher;
        public List<Path> foundPaths = new ArrayList<>();

        public MyFileFinder(String pattern) {
            matcher = FileSystems.getDefault().getPathMatcher("glob:"+pattern);
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            Path name = file.getFileName();
            System.out.println("Examining "+name);
            if (matcher.matches(name)) {
                foundPaths.add(file);
            }
            return FileVisitResult.CONTINUE;
        }
    }

}
