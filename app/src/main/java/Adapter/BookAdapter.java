package Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.carpediem.vv.funny.R;

import java.util.List;

import bean.FunnyGIF.FunnyGif;

/**
 * Created by Administrator on 2016/8/24.
 */
public class BookAdapter extends RecyclerView.Adapter {
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;
    private Activity mActivity;
    private List<FunnyGif> mDatas;

    public BookAdapter(Context mActivity, List<FunnyGif> data) {
        this.mActivity = (Activity) mActivity;
        this.mDatas = data;
    }

     public void setData( List<FunnyGif> data) {
         this.mDatas = data;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    @Override
    public int getItemCount() {
        return mDatas.size() == 0 ? 10 : mDatas.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
     /*   if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(mActivity).inflate(R.layout.item_with_data, parent,
                    false);
            return new ItemViewHolder(view);
        } else if (viewType == TYPE_FOOTER) {
            View view = LayoutInflater.from(mActivity).inflate(R.layout.item_foot, parent,
                    false);
            return new FootViewHolder(view);
        }
        return null;*/
        View view = LayoutInflater.from(mActivity).inflate(R.layout.item_books, parent, false);
        return new ItemViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
//        String textContent = mDatas.get(position).getTextContent();
      /*  if (!textContent.isEmpty()) {
            Log.e("bmob查询的数据",textContent+"textContent");
            ((ItemViewHolder) holder).textCategory.setText(textContent);

        }*/

      /*  if (holder instanceof ItemViewHolder) {
            //holder.tv.setText(data.get(position));
            if (onItemClickListener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = holder.getLayoutPosition();
                        onItemClickListener.onItemClick(holder.itemView, position);
                    }
                });

                holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        int position = holder.getLayoutPosition();
                        onItemClickListener.onItemLongClick(holder.itemView, position);
                        return false;
                    }
                });
            }
        }*/
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView textCategory;
        private final ImageView imageViewOne;
        private final ImageView imageViewTwo;
        private final ImageView imageViewThree;

        public ItemViewHolder(View view) {
            super(view);
            textCategory = (TextView) view.findViewById(R.id.text_category);
            imageViewOne = (ImageView) view.findViewById(R.id.book_one);
            imageViewTwo = (ImageView) view.findViewById(R.id.book_two);
            imageViewThree = (ImageView) view.findViewById(R.id.book_three);
        }
    }

    static class FootViewHolder extends RecyclerView.ViewHolder {

        public FootViewHolder(View view) {
            super(view);
        }
    }
}
