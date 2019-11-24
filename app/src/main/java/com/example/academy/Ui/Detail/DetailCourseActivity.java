package com.example.academy.Ui.Detail;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.academy.Data.source.local.entity.CourseEntity;
import com.example.academy.Data.source.local.entity.ModuleEntity;
import com.example.academy.R;
import com.example.academy.Ui.Reader.CourseReaderActivity;
import com.example.academy.Utils.DataDummy;
import com.example.academy.viewmodel.viewModelVactory;

import java.util.List;

public class DetailCourseActivity extends AppCompatActivity {


    public static final String EXTRA_COURSE = "extra_course";
    Toolbar toolbar;
    private Button btnStart;
    private TextView textDesc;
    private TextView textTitle;
    private TextView textDate;
    private RecyclerView rvModule;
    private DetailCourseAdapter adapter;
    private ImageView imagePoster;
    private ProgressBar progressBar;
    private DetailCourseActivity detailCourseActivity;
    private List<ModuleEntity> courseMod;
    private DetailCourseViewModel detailCourseViewModel;
    String idCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_course);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        idCourse = getIntent().getExtras().getString(EXTRA_COURSE);
        // initialisasi viewmodel from class repository
        detailCourseViewModel = obtainViewModel(this);

        progressBar = findViewById(R.id.progress_bar);
        btnStart = findViewById(R.id.btn_start);
        textTitle = findViewById(R.id.text_title);
        textDesc = findViewById(R.id.text_description);
        textDate = findViewById(R.id.text_date);
        rvModule = findViewById(R.id.rv_module);
        imagePoster = findViewById(R.id.image_poster);
        detailCourseViewModel.setCourseId(idCourse);
        adapter = new DetailCourseAdapter();

        detailCourseViewModel.getModules().observe(this, moduleEntities -> {
            progressBar.setVisibility(View.GONE);
            Log.d("Berhasil", "onCreate: " + moduleEntities.get(0).getmTitle());
            adapter.setModules(moduleEntities);
            adapter.notifyDataSetChanged();
        });

        detailCourseViewModel.getCourse().observe(this, courseEntity -> {
            populateCourse(courseEntity);
        });

        // mematikan scrol dalam recyclerview
        rvModule.setNestedScrollingEnabled(false);
        rvModule.setLayoutManager(new LinearLayoutManager(this));
        rvModule.setHasFixedSize(true);
        rvModule.setAdapter(adapter);
        // set divider
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvModule.getContext(), DividerItemDecoration.VERTICAL);
        rvModule.addItemDecoration(dividerItemDecoration);

    }

    private void populateCourse(CourseEntity courseEnti) {
        // get data CouserEntry berdasarkan idCourse
        CourseEntity courseEntity = DataDummy.getCourse(courseEnti.getCourseId());
        textTitle.setText(courseEntity.getTitle());
        textDesc.setText(courseEntity.getDeskription());
        textDate.setText(String.format("Deadline %s", courseEntity.getDeadline()));
        Glide.with(getApplicationContext())
                .load(courseEntity.getImagePath())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                .into(imagePoster);
        // intent to course reader activity
        btnStart.setOnClickListener(v -> {
            Intent intent = new Intent(DetailCourseActivity.this, CourseReaderActivity.class);
            // put data course id into
            intent.putExtra(CourseReaderActivity.EXTRA_COURSE_ID, courseEnti.getCourseId());
            v.getContext().startActivity(intent);
        });
    }

    private static DetailCourseViewModel obtainViewModel(AppCompatActivity activity) {
        viewModelVactory factory = viewModelVactory.getInstance(activity.getApplication());
        return ViewModelProviders.of(activity, factory).get(DetailCourseViewModel.class);
    }
}
