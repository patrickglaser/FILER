package org.apache.tika;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.ToTextContentHandler;
import org.xml.sax.SAXException;

public class MyParser {
	
    public static void main(final String[] args) throws IOException, SAXException, TikaException {
    	
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter path of your pdf file");
        String fileName = scanner.nextLine();
        Parser parser = new AutoDetectParser(); 
        ToTextContentHandler handler = new ToTextContentHandler();
        Metadata metadata = new Metadata();
        FileInputStream inputstream = new FileInputStream(new File(fileName));
        ParseContext pcontext = new ParseContext();

        //parsing the document using AutoDetectParser
        parser.parse(inputstream, handler, metadata, pcontext);
        System.out.println("Contents of the PDF :" + handler.toString());

        //getting metadata of the document
        System.out.println("Metadata of the PDF:");
        String[] metadataNames = metadata.names();

        for (String name : metadataNames) {
            System.out.println(name + " : " + metadata.get(name));
        }

        //save text to a KIID.doc file
        try (PrintStream out = new PrintStream(new FileOutputStream("KIID.doc"))) {
            out.print(handler);
        }

}

}
