package Adapters;

import android.app.AlertDialog;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.assignment3.Activities.ViewModifyActivity;
import com.example.assignment3.R;

import java.util.List;

import Models.StudentDetails;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class StudentDetailsAdapter extends RecyclerView.Adapter<StudentDetailsAdapter.ViewHolder> {

    List<StudentDetails> studentsDetails;

    public boolean isList() {
        return list;
    }

    public void setList(boolean list) {
        this.list = list;
    }

    boolean list;

    public StudentDetailsAdapter(List<StudentDetails> studentsDetails, boolean list) {
        this.studentsDetails = studentsDetails;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        Log.d("onBindViewHolder: ", "chala");
        if (list) {
            return new ViewHolder(inflater.inflate(R.layout.item_student_detail, parent, false));
        }
        else {
            return new ViewHolder(inflater.inflate(R.layout.item_student_data_grid, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.student_name.setText(studentsDetails.get(position).getStudentName());
        holder.student_class.setText(new Integer(studentsDetails.get(position).getStudentClass()).toString());

        holder.parentView.setOnClickListener(new View.OnClickListener() {

            AlertDialog alertDialog;

            @Override
            public void onClick(View v) {
                View alertDialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.layout_alert_dialog, null);
                alertDialog = new AlertDialog.Builder(v.getContext())
                        .setView(alertDialogView).create();
                alertDialog.show();

                Button button_view = alertDialogView.findViewById(R.id.alert_button_view);
                button_view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        handleClickEvents(v, 0);
                    }
                });

                Button button_edit = alertDialogView.findViewById(R.id.alert_button_edit);
                button_edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        handleClickEvents(v, 1);
                    }
                });

                Button button_delete = alertDialogView.findViewById(R.id.alert_button_delete);
                button_delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        studentsDetails.remove(position);
                        notifyDataSetChanged();
                        alertDialog.dismiss();
                    }
                });
            }

            public void handleClickEvents(View v, int value) {
                Intent intent = new Intent(v.getContext(), ViewModifyActivity.class);
                intent.putExtra("detail", studentsDetails.get(position));
                intent.putExtra("flag", value);
                intent.putExtra("position", position);
                v.getContext().startActivity(intent);
                alertDialog.dismiss();
            }
        });
    }

    @Override
    public int getItemCount() {
        return studentsDetails.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView student_name;
        TextView student_class;
        View parentView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parentView = itemView;
            student_name = itemView.findViewById(R.id.textviewtem_name);
            student_class = itemView.findViewById(R.id.textview_item_student_detail_class);
        }
    }
}
