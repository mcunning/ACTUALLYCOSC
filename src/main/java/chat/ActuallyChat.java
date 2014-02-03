package main.java.chat;

public class ActuallyChat implements Chat
{
    boolean chat;
    Responder responder;

    public ActuallyChat()
    {
        chat = true;
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
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void chat()
    {
        // TODO greet;

        // TODO Auto-generated method stub
        while( chat )
        {
            String sentence = getSentence();

            responder.respond( sentence );
        }
    }
}
