package me.kindeep.simarmemegenerator;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.FaceDetector;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.Console;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.obama);

        FaceDetector detector = new FaceDetector(image.getWidth(), image.getHeight(), 5);

        FaceDetector.Face[] faces = new FaceDetector.Face[5];

        detector.findFaces(image, faces);


        Log.v("YOOOO", Integer.toString(faces.length) + faceToString(faces));


    }

    protected String faceToString(FaceDetector.Face[] faces) {
        String result = "";
        for (FaceDetector.Face face : faces) {
            if (face != null)
                result += Double.toString(face.confidence());
            else result += "fuck";
        }
        return result;
    }
}
