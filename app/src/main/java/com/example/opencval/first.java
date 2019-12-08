package com.example.opencval;


import android.annotation.TargetApi;
import android.app.Application;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Mat;

import java.util.Collections;
import java.util.List;

import static android.Manifest.permission.CAMERA;

public class first extends AppCompatActivity    implements CameraBridgeViewBase.CvCameraViewListener2 {
    Button Save;
    Button Next;
    Button Nsave;

    private static final String TAG = "opencv";
    private Mat matInput;

    private CameraBridgeViewBase mOpenCvCameraView;


    static {
        System.loadLibrary("opencv_java4");
        System.loadLibrary("native-lib");
    }

    class MyApp extends Application {

        private String myState;

        public String getState(){
            return myState;
        }
        public void setState(String s){
            myState = s;
        }
    }

    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                case LoaderCallbackInterface.SUCCESS:
                {
                    mOpenCvCameraView.enableView();
                } break;
                default:
                {
                    super.onManagerConnected(status);
                } break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.rgbset);

        Next = (Button) findViewById(R.id.button_go);
        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(first.this,categoryselect.class);
                intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        mOpenCvCameraView = (CameraBridgeViewBase)findViewById(R.id.activity_surface_view);
        mOpenCvCameraView.setVisibility(SurfaceView.VISIBLE);
        mOpenCvCameraView.setCvCameraViewListener(this);
        mOpenCvCameraView.setCameraIndex(1); // front-camera(1),  back-camera(0)
    }

    @Override
    public void onPause()
    {
        super.onPause();
        if (mOpenCvCameraView != null)
            mOpenCvCameraView.disableView();
    }



    @Override
    public void onResume()
    {
        super.onResume();

        if (!OpenCVLoader.initDebug()) {
            Log.d(TAG, "onResume :: Internal OpenCV library not found.");
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_2_0, this, mLoaderCallback);
        } else {
            Log.d(TAG, "onResum :: OpenCV library found inside package. Using it!");
            mLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        }
    }


    public void onDestroy() {
        super.onDestroy();

        if (mOpenCvCameraView != null)
            mOpenCvCameraView.disableView();
    }

    @Override
    public void onCameraViewStarted(int width, int height) {

    }

    @Override
    public void onCameraViewStopped() {

    }


    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {

        matInput = inputFrame.rgba();

        int c = matInput.cols();
        final int r = matInput.rows();

        final double[] a = matInput.get(5, 176);
        final double[] b = matInput.get(280, 176);

        int red = (int) Math.round(a[0]);
        int green = (int) Math.round(a[1]);
        int blue = (int) Math.round(a[2]);
        ;

        int redb = (int) Math.round(b[0]);
        int greenb = (int) Math.round(b[1]);
        int blueb = (int) Math.round(b[2]);


        Nsave = (Button) findViewById(R.id.button_nsave);
        Nsave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                rgbset neR = (rgbset) getApplication();
                neR.setNextred((int) Math.round(a[0]));

                rgbset neG = (rgbset) getApplication();
                neG.setNextgreen((int) Math.round(a[1]));

                rgbset neB = (rgbset) getApplication();
                neG.setNextblue((int) Math.round(a[2]));
            }
        });


        Save = (Button) findViewById(R.id.button_save);
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rgbset beR = (rgbset) getApplication();
                beR.setBeforered((int) Math.round(b[0]));

                rgbset beG = (rgbset) getApplication();
                beG.setBeforegreen((int) Math.round(b[1]));

                rgbset beB = (rgbset) getApplication();
                beB.setBeforeblue((int) Math.round(b[2]));

            }
        });

        return matInput;

    }



    protected List<? extends CameraBridgeViewBase> getCameraViewList() {
        return Collections.singletonList(mOpenCvCameraView);
    }


    //여기서부턴 퍼미션 관련 메소드
    private static final int CAMERA_PERMISSION_REQUEST_CODE = 200;


    protected void onCameraPermissionGranted() {
        List<? extends CameraBridgeViewBase> cameraViews = getCameraViewList();
        if (cameraViews == null) {
            return;
        }
        for (CameraBridgeViewBase cameraBridgeViewBase: cameraViews) {
            if (cameraBridgeViewBase != null) {
                cameraBridgeViewBase.setCameraPermissionGranted();
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        boolean havePermission = true;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{CAMERA}, CAMERA_PERMISSION_REQUEST_CODE);
                havePermission = false;
            }
        }
        if (havePermission) {
            onCameraPermissionGranted();
        }
    }

    @Override
    @TargetApi(Build.VERSION_CODES.M)
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE && grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            onCameraPermissionGranted();
        }else{
            showDialogForPermission("앱을 실행하려면 퍼미션을 허가하셔야합니다.");
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    @TargetApi(Build.VERSION_CODES.M)
    private void showDialogForPermission(String msg) {

        AlertDialog.Builder builder = new AlertDialog.Builder( first.this);
        builder.setTitle("알림");
        builder.setMessage(msg);
        builder.setCancelable(false);
        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id){
                requestPermissions(new String[]{CAMERA}, CAMERA_PERMISSION_REQUEST_CODE);
            }
        });
        builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                finish();
            }
        });
        builder.create().show();
    }



}