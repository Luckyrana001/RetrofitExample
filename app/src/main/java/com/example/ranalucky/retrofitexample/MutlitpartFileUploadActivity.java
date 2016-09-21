package com.example.ranalucky.retrofitexample;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.ranalucky.retrofitexample.R;
import com.example.ranalucky.retrofitexample.services.MultiPartService;
import com.example.ranalucky.retrofitexample.utils.BaseActivity;
import com.example.ranalucky.retrofitexample.utils.ConnectionDetector;
import com.example.ranalucky.retrofitexample.utils.Constants;
import com.example.ranalucky.retrofitexample.utils.Utility;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Rana lucky on 8/27/2016.
 */
public class  MutlitpartFileUploadActivity extends AppCompatActivity {
    ImageView profileImg;
    static final int PICK_IMAGE_REQUEST = 1;
    public String filePath;
    MultiPartService service;
    EditText userNameEt,passwordEdit;
    TextView passwordError,userNameError;
    Button confirmBtn;
    File file = null;
    ConnectionDetector cd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setLocale(0);
       /* if(Constants.START_SPLUNK_MINT) {
            Mint.setApplicationEnvironment(Mint.appEnvironmentTesting);
            Mint.initAndStartSession(SignUpActivity.this, "74f4f6e6");
        }*/
        setContentView(R.layout.sign_up_layout);

        cd = new ConnectionDetector();
        setLayout();

        if(getCurrentFocus()!=null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
         /* getMenuInflater().inflate(R.menu.menu_staff_home, menu);
          MenuItem arrived = menu.findItem(R.id.action_arrived);
          arrived.setVisible(false);

          MenuItem actionSearchbtn = menu.findItem(R.id.action_search_btn);
          actionSearchbtn.setVisible(true);
  */
        /*  MenuItem actionSearch = menu.findItem(R.id.action_search);
        actionSearch.setVisible(false);
*/
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;


        }
        return super.onOptionsItemSelected(item);
    }

    private void setLayout() {
       /* Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
*/
        confirmBtn =  (Button) findViewById(R.id.confirm_btn);
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(cd.isConnectingToInternet(getApplicationContext())) {
                        checkValidity();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"No Internet Connection.",Toast.LENGTH_SHORT).show();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        profileImg = (ImageView)findViewById(R.id.profile_img);

        profileImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageBrowse();

            }
        });
        userNameEt = (EditText)findViewById(R.id.user_name_et);
        passwordEdit = (EditText)findViewById(R.id.password_edit);


        userNameError = (TextView)findViewById(R.id.user_name_edit_error);
        passwordError = (TextView)findViewById(R.id.password_error);


        SharedPreferences prefs = getApplicationContext(). getSharedPreferences(Constants.MY_PREFS_NAME,getApplicationContext().MODE_PRIVATE);
        String  userName = prefs.getString("userName", "");
        userNameEt.setText(Utility.checkNullString(userName));
        String  pswd = prefs.getString("oldPassword", "");
        passwordEdit.setText(Utility.checkNullString(pswd));


    }


    private void imageBrowse() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        // Start the Intent
        startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {


            if(requestCode == 01)
            {
                Uri picUri = data.getData();

                filePath = getPath(picUri);

                Log.d("picUri", picUri.toString());
                Log.d("filePath", filePath);
                profileImg.setImageURI(picUri);
               /* Glide.with(this)
                        .load(picUri)
                        .bitmapTransform(new CropCircleTransformation(this))
                        .into(profileImg);*/




            }
            if(requestCode == PICK_IMAGE_REQUEST){
                Uri picUri = data.getData();

                filePath = getPath(picUri);

                Log.d("picUri", picUri.toString());
                Log.d("filePath", filePath);

                // profileImg.setImageURI(picUri);

                try {

                    //call the standard crop action intent (the user device may not support it)
                    Intent intent = new Intent("com.android.camera.action.CROP");

                    File file = new File(filePath);
                    Uri uri = Uri.fromFile(file);
                    intent.setData(uri);
                    intent.putExtra("crop", "true");
                    intent.putExtra("aspectX", 1);
                    intent.putExtra("aspectY", 1);
                    intent.putExtra("outputX", 96);
                    intent.putExtra("outputY", 96);
                    intent.putExtra("noFaceDetection", true);
                    intent.putExtra("return-data", true);
                    startActivityForResult(intent, 01);
                }
                catch(ActivityNotFoundException anfe){
                    //display an error message
                   /* String errorMessage = "Whoops - your device doesn't support the crop action!";
                    Toast toast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
                    toast.show();
                   */
                    profileImg.setImageURI(picUri);
                    /*Glide.with(this)
                            .load(picUri)
                            .bitmapTransform(new CropCircleTransformation(this))
                            .into(profileImg);*/
                }
            }

        }

    }

    private String getPath(Uri contentUri) {
        String[] proj = { MediaStore.Images.Media.DATA };
        CursorLoader loader = new CursorLoader(getApplicationContext(), contentUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();
        return result;
    }
    private void checkValidity() throws IOException {
        if(userNameEt.getText().toString().trim().equals(""))
        {
            userNameError.setVisibility(View.VISIBLE);
            passwordError.setVisibility(View.GONE);

        }
        else if(passwordEdit.getText().toString().trim().length()<5)
        {
            userNameError.setVisibility(View.GONE);
            passwordError.setVisibility(View.VISIBLE);

        }
        else
        {
            userNameError.setVisibility(View.GONE);
            passwordError.setVisibility(View.GONE);



          /*  Intent intent = new Intent(getApplicationContext(), ChangePasswordActivity.class);
                intent.putExtra("userName",userNameEt.getText().toString().trim());
                intent.putExtra("pass",passwordEdit.getText().toString().trim());
                intent.putExtra("filePath",filePath);
            startActivity(intent);

*/



            postSignUpData(userNameEt.getText().toString().trim(),passwordEdit.getText().toString().trim(),filePath);

           // postSignUpData("1045093943","mda@123",filePath);
            //submitRequrst(userNameEt.getText().toString().trim(),passwordEdit.getText().toString().trim(),"");
        }

    }

    private void postSignUpData(String userName, String pass, String fileuri) throws IOException {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        // Change base URL to your upload server URL.
        service = new Retrofit.Builder().baseUrl(Constants.BASE_URL).client(client).build().create(MultiPartService.class);

        if(fileuri!=null)
        {
            file = new File(fileuri);
        }
        else
        {
          //  if user do not select any image , then uploading dummy image...
            createDummyImage(getApplicationContext());
            //  file = new File(getResources().getDrawable(R.drawable.avatar));

        }
        RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("user[profile_photo]", "user_pic", reqFile);
        RequestBody name = RequestBody.create(MediaType.parse("text/plain"), userName);
        RequestBody password = RequestBody.create(MediaType.parse("text/plain"), pass);
        RequestBody newPassword = RequestBody.create(MediaType.parse("text/plain"), pass);
        // String newPswd = "mda@1234";

        RequestBody deviceType = RequestBody.create(MediaType.parse("text/plain"), "android");
        RequestBody uuid = RequestBody.create(MediaType.parse("text/plain"),"");

        // AgentDetails agent = DataHolderSingleton.getsInstance().getLoggedInAgent();

        // String auth = "Bearer "+agent.getAccess_token();


//            Log.d("THIS", data.getData().getPath());


        retrofit2.Call<okhttp3.ResponseBody> req = service.postImage(/*auth,*/body, name,password,password,newPassword,newPassword,deviceType,uuid);
        //retrofit2.Call<okhttp3.ResponseBody> req = service.postImage(auth, name,password);


        req.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                Log.i("",response+"");
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void createDummyImage(Context context) throws IOException {


        //create a file to write bitmap data
        file = new File(context.getCacheDir(), "profImage");
        file.createNewFile();


        Drawable myDrawable = getResources().getDrawable(R.drawable.avatar);
        Bitmap bitmap = ((BitmapDrawable)myDrawable).getBitmap();
//Convert bitmap to byte array
        //Bitmap bitmap = your bitmap;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0 /*ignored for PNG*/, bos);
        byte[] bitmapdata = bos.toByteArray();

//write the bytes in file
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(bitmapdata);
        fos.flush();
        fos.close();
    }




   /* private void submitRequrst(String user, String pass, String filePath) {
        OperationAsync.signUpAsyc("user", pass, new signUpUser(), SignUpActivity.this, true, R.string.please_wait);

    }


    public class signUpUser implements ResultListener<SignUpRequest.LoginUserResp> {

        @Override
        public void onResultsSucceded(SignUpRequest.LoginUserResp result) {
            if(result.isLoginSuccessful())
            {
                showProgress(false);



                Intent intent = new Intent(getApplicationContext(), ChangePasswordActivity.class);
                startActivity(intent);
                finish();
                //  Log.i(" String Response" ,result.getResponse() );


                //CheckApiVersionModel model = new Gson().fromJson(result.getResponse(),CheckApiVersionModel.class);



            }

        }
        @Override
        public void onResultsFail() {
        }
    }*/
    /**
     * Shows the progress UI and hides the login form.
     */
    /*@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        if(show) {
            progressDialog = new MaterialDialog.Builder(SignUpActivity.this)
                    .content(R.string.please_wait)
                    .autoDismiss(false)
                    .cancelable(false)
                    .progress(true, 0).show();
        }
        else {
            if(progressDialog != null && !progressDialog.isCancelled())
                progressDialog.cancel();
        }

    }*/
   /* @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
   */ }
