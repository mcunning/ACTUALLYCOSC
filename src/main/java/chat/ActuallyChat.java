package main.java.chat;

import java.util.Scanner;

public class ActuallyChat implements Chat
{
    boolean chat;
    Responder responder;
    Scanner scan;

    public ActuallyChat()
    {
        chat = true;
        scan = new Scanner( System.in );
        // TODO
    }

    @Override
    public void initialize( Responder responderIn )
    {
        responder = responderIn;
        responder.readConfigFile();

    }

    @Override
    public String getSentence()
    {
        return scan.nextLine();
    }

    private void print( String string )
    {
    	System.out.println( string );
    }
    @Override
    public void chat()
    {
        // TODO greet;
    	print( "Welcome to Chat." );
    	
        // TODO Auto-generated method stub
        while( chat )
        {
            String sentence = getSentence();

            responder.respond( sentence );
        }
    }
}
