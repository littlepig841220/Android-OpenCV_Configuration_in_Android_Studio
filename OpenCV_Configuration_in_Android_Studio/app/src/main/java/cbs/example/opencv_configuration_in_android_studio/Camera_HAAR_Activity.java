package cbs.example.opencv_configuration_in_android_studio;

import android.content.Context;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.WindowManager;
import android.widget.Toast;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraActivity;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

public class Camera_HAAR_Activity extends CameraActivity implements CameraBridgeViewBase.CvCameraViewListener2{
    private CameraBridgeViewBase cameraBridgeViewBase;
    private CascadeClassifier faceDetector;
    private Mat src,gray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_preview);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        cameraBridgeViewBase = (CameraBridgeViewBase) findViewById(R.id.cameraView);
        cameraBridgeViewBase.setCameraIndex(1);
        cameraBridgeViewBase.setVisibility(SurfaceView.VISIBLE);
        cameraBridgeViewBase.setCvCameraViewListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (cameraBridgeViewBase != null)
            cameraBridgeViewBase.disableView();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!OpenCVLoader.initDebug()) {
            Toast.makeText(getApplicationContext(),"Internal OpenCV library not found. Using OpenCV Manager for initialization",Toast.LENGTH_SHORT).show();
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_0_0, this, mLoaderCallback);
        } else {
            Toast.makeText(getApplicationContext(),"OpenCV library found inside package. Using it!",Toast.LENGTH_SHORT).show();
            mLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        }
    }

    public void onDestroy() {
        super.onDestroy();
        if (cameraBridgeViewBase != null)
            cameraBridgeViewBase.disableView();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            Fullscreen_setting fullscreenSetting = new Fullscreen_setting();
            fullscreenSetting.window = getWindow();
            fullscreenSetting.fullscreen();
        }
    }//fullscreen setting

    @Override
    protected List<? extends CameraBridgeViewBase> getCameraViewList() {
        return Collections.singletonList(cameraBridgeViewBase);
    }

    @Override
    public void onCameraViewStarted(int width, int height) {
        src = new Mat();
        gray = new Mat();
    }

    @Override
    public void onCameraViewStopped() {
        src.release();
        gray.release();
    }

    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
        src = inputFrame.rgba();
        gray = inputFrame.gray();

        MatOfRect faces = new MatOfRect();
        faceDetector.detectMultiScale(gray,faces,1.1,3,0,new Size(50,50),new Size());

        List<Rect> face_list = faces.toList();

        if (face_list.size() > 0){
            for (Rect rect : face_list){
                Imgproc.rectangle(src,rect.tl(),rect.br(),new Scalar(255,0,0),2,8,0);
            }
        }
        return src;
    }

    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                case LoaderCallbackInterface.SUCCESS: {
                    try {
                        InputStream inputStream = getResources().openRawResource(R.raw.haarcascade_frontalface_alt2);

                        File cascadeDir = getDir("cascade", Context.MODE_PRIVATE);
                        File file = new File(cascadeDir.getAbsoluteFile(),"haarcascade_frontalface_alt2.xml");

                        FileOutputStream fileOutputStream = new FileOutputStream(file);
                        byte[] buffer = new byte[1024];
                        int bytes_read = 0;
                        while ((bytes_read = inputStream.read(buffer)) != -1){
                            fileOutputStream.write(buffer,0,bytes_read);
                        }

                        inputStream.close();
                        fileOutputStream.close();

                        faceDetector = new CascadeClassifier(file.getAbsolutePath());
                        cascadeDir.delete();
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                    cameraBridgeViewBase.enableView();
                    break;
                }
                default: {
                    super.onManagerConnected(status);
                    break;
                }
            }
        }
    };
}