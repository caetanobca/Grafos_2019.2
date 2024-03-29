import java.io.*;

public class ImportGraph<V, E, VP, EP>
{
    public static StringReader readFile(String filename)
    {
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filename)))
        {
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null)
            {
                contentBuilder.append(sCurrentLine).append("\n");
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        StringReader readergml = new StringReader(contentBuilder.toString());
        return readergml;
    }

}