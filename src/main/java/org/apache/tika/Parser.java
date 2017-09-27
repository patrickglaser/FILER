	package org.apache.tika;

	import java.io.File;
	import java.io.FileInputStream;
	import java.io.FileNotFoundException;
	import java.io.FileOutputStream;
	import java.io.IOException;
	import java.io.PrintStream;
	import java.util.Scanner;

	import org.apache.tika.exception.TikaException;
	import org.apache.tika.metadata.Metadata;
	import org.apache.tika.parser.AutoDetectParser;
	import org.apache.tika.parser.ParseContext;
	import org.apache.tika.sax.BodyContentHandler;
	import org.apache.tika.sax.ToXMLContentHandler;
	import org.xml.sax.SAXException;

public class Parser {
	
	   Scanner scanner;
	   String fileName;
	   ToXMLContentHandler handler;
	   Metadata metadata;
	   FileInputStream inputstream;
	   ParseContext pcontext;
	   AutoDetectParser parser;


	   public Parser() throws FileNotFoundException
	   {
		   this.scanner = new Scanner(System.in);
		   this.fileName=scanner.nextLine();
		   this.handler = new ToXMLContentHandler();
		   this.metadata = new Metadata();
		   this.inputstream = new FileInputStream(new File(fileName));
		   this.pcontext = new ParseContext();
		   this.parser = new AutoDetectParser();
	   }
	   
	   
	   

	public void main(final String[] args) throws IOException, SAXException, TikaException {

	      System.out.println("enter path of your pdf file");	      
	   
	      //parsing the document using AutoDetectParser
	      
	      parser.parse(inputstream, handler, metadata,pcontext);
	   
	      //getting the content of the document
	      System.out.println("Contents of the PDF :" + handler.toString());
	   
	      //getting metadata of the document
	      System.out.println("Metadata of the PDF:");
	      String[] metadataNames = metadata.names();
	      
	      for(String name : metadataNames) {    
	         System.out.println(name+ " : " + metadata.get(name));
	      }
	      
	      //save text to a KIID.doc file
	      try (PrintStream out = new PrintStream(new FileOutputStream("KIID.doc"))) {
	    	    out.print(handler);
	    	}
	      
	   }

	public ToXMLContentHandler getHandler()

	{
		return handler;
	}

	}

