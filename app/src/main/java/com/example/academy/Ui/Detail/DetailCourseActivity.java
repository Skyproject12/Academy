package com.example.academy.Ui.Detail;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
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
    private Menu menu;

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

        detailCourseViewModel.setCourseId(idCourse);
        // call viewmodel where course all select
        detailCourseViewModel.courseModule.observe(this, courseWithModuleResource->{
            if(courseWithModuleResource!=null){
                switch (courseWithModuleResource.status){
                    case Loading:
                        progressBar.setVisibility(View.VISIBLE);
                        break;
                    case SUCCESS:
                        if(courseWithModuleResource.data!=null){
                            progressBar.setVisibility(View.GONE);
                            adapter.setModules(courseWithModuleResource.data.mModule);
                            adapter.notifyDataSetChanged();
                            // set populate course
                            populateCourse(courseWithModuleResource.data.mCourse);
                        }
                        break;
                    case ERROR:
                        progressBar.setVisibility(View.GONE);
                        break;

                }
            }
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        this.menu= menu;
        detailCourseViewModel.courseModule.observe(this, courseWithModule->{
            if(courseWithModule!=null){
                switch (courseWithModule.status){
                    case Loading:
                        progressBar.setVisibility(View.VISIBLE);
                        break;
                    case SUCCESS:
                        if(courseWithModule.data!=null){
                            progressBar.setVisibility(View.GONE);
                            // set courseWithModule check is state or not
                            boolean state= courseWithModule.data.mCourse.isBookmarked();
                            setBookmarkState(state);
                        }
                        break;
                    case ERROR:
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(detailCourseActivity, "Terjadi kesalaha", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.action_bookmark){
            detailCourseViewModel.setBookmark();
            return true;

        }
        return super.onOptionsItemSelected(item);
    }

    private void setBookmarkState(boolean state){
        if(menu==null) return;
        // set drawable apakah di bookmarked atau tidak
        MenuItem menuItem= menu.findItem(R.id.action_bookmark);
        if(state){
            menuItem.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_bookmark_border));
        }
        else{
            menuItem.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_bookmark_white));
        }
    }

    private static DetailCourseViewModel obtainViewModel(AppCompatActivity activity) {
        viewModelVactory factory = viewModelVactory.getInstance(activity.getApplication());
        return ViewModelProviders.of(activity, factory).get(DetailCourseViewModel.class);
    }
}
