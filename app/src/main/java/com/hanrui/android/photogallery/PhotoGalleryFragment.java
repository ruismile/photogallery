package com.hanrui.android.photogallery;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * @author
 * @version 1.0
 * @date 2017/6/18 0018
 */

public class PhotoGalleryFragment extends Fragment{
    private static final String TAG="PhotoGalleryFragment";
    private RecyclerView mRecyclerView;
    private List<GalleryItem>mItems = new ArrayList<>();
    
    public static PhotoGalleryFragment newInstance(){
        return new PhotoGalleryFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);//Activity重新创建时可以不完全销毁Fragment，以便Fragment可以恢复
        new FetchItemsTask().execute();//启动AsyncTask
    }
    
    //创建Fragment视图，把RecycleView添加到Fragment中
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View v=inflater.inflate(R.layout.fragment_photo_gallery,container,false);
        
        mRecyclerView=(RecyclerView)v.findViewById(R.id.fragment_photo_gallery_recycle_view);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));
        
        setupAdapter();
        
        return v;
    }
    
    private void setupAdapter(){
        if(isAdded()){
            mRecyclerView.setAdapter(new PhotoAdapter(mItems));
        }
    }
    
    private class PhotoHolder extends RecyclerView.ViewHolder{
        private TextView mTitleTextView;
        
        public PhotoHolder(View itemView){
            super(itemView);
            
            mTitleTextView=(TextView)itemView;
        }
        
        public void bindGalleryItem(GalleryItem item){
            mTitleTextView.setText(item.toString());
        }
    }
    
    private class PhotoAdapter extends RecyclerView.Adapter<PhotoHolder>{
        private List<GalleryItem>mGalleryItems;
        
        public PhotoAdapter(List<GalleryItem>galleryItems){
            mGalleryItems=galleryItems;
        }
        
        public PhotoHolder onCreateViewHolder(ViewGroup viewGroup,int viewType){
            TextView textView=new TextView(getActivity());
            return new PhotoHolder(textView);
        }
        
        public void onBindViewHolder(PhotoHolder photoHolder,int position){
            GalleryItem galleryItem=mGalleryItems.get(position);
            photoHolder.bindGalleryItem(galleryItem);
         }

        @Override
        public int getItemCount() {
            return mGalleryItems.size();
        }
    }
    
    
    //从目标网站获取数据并记录日志
    private class FetchItemsTask extends AsyncTask<Void,Void,List<GalleryItem>>{
        protected List<GalleryItem> doInBackground(Void... params){
            return new FlickrFetchr().fetchItems();
        }

        @Override
        protected void onPostExecute(List<GalleryItem> items) {
           mItems=items;
            setupAdapter();
        }
    }
}
