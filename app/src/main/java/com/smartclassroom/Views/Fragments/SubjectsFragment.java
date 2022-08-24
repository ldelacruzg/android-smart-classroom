package com.smartclassroom.Views.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.smartclassroom.Adapters.SubjectExpandableListAdapter;
import com.smartclassroom.Models.Subject;
import com.smartclassroom.Models.Teacher;
import com.smartclassroom.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubjectsFragment extends Fragment {
    View view;

    ExpandableListView listViewSubjects;
    List<String> subjectListGroup;
    Map<String, List<String>> subjectListGroupChild;

    Teacher teacher = new Teacher("Orlando", "Erazo", "oerazo@uteq.edu.ec");

    public SubjectsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_subjects, container, false);

        // init components
        initComponents();

        // build list view
        buildList();

        return view;
    }

    private void initComponents() {
        listViewSubjects = view.findViewById(R.id.listViewSubjects);
    }

    private Map<String, List<String>> getList() {
        subjectListGroupChild = new HashMap<>();
        for (Subject subject : subjectListGroup()) {
            subjectListGroupChild.put(subject.getName(), new ArrayList<String>() {
                {
                    add("Students");
                    add("Attendance");
                }
            });
        }

        return subjectListGroupChild;
    }

    private List<Subject> subjectListGroup() {
        /*subjectListGroup = new ArrayList<>();
        subjectListGroup.add("Programación Orientada a Objectos");
        subjectListGroup.add("Aplicaciones Web");
        subjectListGroup.add("Aplicaciones Móviles");

        return subjectListGroup;*/

        /*return new ArrayList<Teacher>() {{
            add(new Teacher("Orlando", "Erazo", "oerazo@uteq.edu.ec"));
        }};*/

        return new ArrayList<Subject>() {{
           add(new Subject("Object-oriented programming", teacher));
           add(new Subject("Web Applications", teacher));
           add(new Subject("Mobile Applications", teacher));
        }};
    }

    private void buildList() {
        // Option 1 Simple Item
        // Adaptador, la forma en que mostraremos los datos (item)
        /*
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getContext(),
                android.R.layout.simple_list_item_1,
                getData()
        );

        // Se enlaza el adapter con el listView
        listViewSubjects.setAdapter(adapter);
        */

        // Option 2 Custom Item
        /*
        SubjectListItemAdapter adapter = new SubjectListItemAdapter(
                getContext(),
                R.layout.subject_list_group_child,
                getData()
        );

        listViewSubjects.setAdapter(adapter);
         */

        // Option 3 Expandable List View
        SubjectExpandableListAdapter adapter = new SubjectExpandableListAdapter(
                getContext(),
                subjectListGroup(),
                getList()
        );

        listViewSubjects.setAdapter(adapter);
    }
}