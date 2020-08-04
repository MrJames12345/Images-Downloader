import java.util.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
// Webscraping
import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;
//Downloading image
import java.nio.file.Files;
import java.net.URL;


class ImageDownloader
{


// - Downloading Image - //


    // Download any image given url and destination
    public static void downloadImage( String inUrl, String inDestination ) throws IOException, UnsupportedImageException
    {

        byte[] imageBytes = ImageDownloader.downloadImageAsBytes( "https://img.discogs.com/CsjQQ789z29gVi1n4jujxEMLx1A=/fit-in/600x600/filters:strip_icc():format(jpeg):mode_rgb():quality(90)/discogs-images/R-12715516-1540591035-1207.png.jpg" );

        FileOutputStream outStrm = new FileOutputStream( inDestination ); 
        outStrm.write( imageBytes );

    }


// - Get Image as Bytes - //


    // Instead of downloading to file, get as array of bytes
    public static byte[] downloadImageAsBytes( String inUrl ) throws IOException, UnsupportedImageException
    {
        URL imageUrl = null;
        BufferedImage originalImage = null;
        ByteArrayOutputStream byteOutStrm = null;
        byte[] imageBytes = null;

        try
        {
            imageUrl = new URL( inUrl );
            originalImage = ImageIO.read( imageUrl );
            byteOutStrm = new ByteArrayOutputStream();

            // IF unable to get image, throw exception
            if ( imageUrl == null || originalImage == null || byteOutStrm == null )
            {
                throw new UnsupportedImageException();
            }

            ImageIO.write( originalImage, "jpg", byteOutStrm );

            imageBytes = byteOutStrm.toByteArray();
        }
        // IF unable to get image, throw exception
        catch( IOException e )
        {
            throw new UnsupportedImageException();
        }

        return imageBytes;
    }

}