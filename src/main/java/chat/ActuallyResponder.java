package main.java.chat;

import java.util.List;

import main.java.chat.component.Keyword;

public class ActuallyResponder implements Responder
{
    private static List<Keyword> keywords;

    public ActuallyResponder()
    {
        // TODO
    }

    @Override
    public void readConfigFile()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void respond( String inputSentence )
    {
        // TODO

        for( Keyword keyword : keywords )
        {
            // TODO
        }
    }

}
