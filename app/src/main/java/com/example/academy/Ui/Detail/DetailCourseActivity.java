package com.example.academy.Ui.Detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.academy.R;
import com.example.academy.Ui.Data.CourseEntity;
import com.example.academy.Ui.Reader.CourseReaderActivity;
import com.example.academy.Utils.DataDummy;

public class DetailCourseActivity extends AppCompatActivity {


    public static final String EXTRA_COURSE ="extra_course" ;
    Toolbar toolbar;
    private Button btnStart;
    private TextView textDesc;
    private TextView textTitle;
    private TextView textDate;
    private RecyclerView rvModule;
    private DetailCourseAdapter adapter;
    private ImageView imagePoster;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_course);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        adapter= new DetailCourseAdapter(DataDummy.generateDummyModules(getIntent().getExtras().getString(EXTRA_COURSE)));
        progressBar= findViewById(R.id.progress_bar);
        btnStart= findViewById(R.id.btn_start);
        textTitle= findViewById(R.id.text_title);
        textDesc= findViewById(R.id.text_description);
        textDate= findViewById(R.id.text_date);
        rvModule= findViewById(R.id.rv_module);
        imagePoster= findViewById(R.id.image_poster);

        // set data for detail
        populateCourse(getIntent().getExtras().getString(EXTRA_COURSE));

        // mematikan scrol dalam recyclerview
        rvModule.setNestedScrollingEnabled(false);
        rvModule.setLayoutManager(new LinearLayoutManager(this));
        rvModule.setHasFixedSize(true);
        rvModule.setAdapter(adapter);
        // set divider
        DividerItemDecoration dividerItemDecoration= new DividerItemDecoration(rvModule.getContext(), DividerItemDecoration.VERTICAL);
        rvModule.addItemDecoration(dividerItemDecoration);

    }
    private void populateCourse(String courseId){
        // get data CouserEntry berdasarkan idCourse
        CourseEntity courseEntity= DataDummy.getCourse(courseId);
        textTitle.setText(courseEntity.getTitle());
        textDesc.setText(courseEntity.getDeskription());
        textDate.setText(String.format("Deadline %s", courseEntity.getDeadline()));
        Glide.with(getApplicationContext())
                .load(courseEntity.getImagePath())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                .into(imagePoster);
        // intent to course reader activity
        btnStart.setOnClickListener(v->{
            Intent intent= new Intent(DetailCourseActivity.this, CourseReaderActivity.class);
            // put data course id into
            intent.putExtra(CourseReaderActivity.EXTRA_COURSE_ID, courseId);
            v.getContext().startActivity(intent);
        });
    }
}
