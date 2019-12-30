package List_of_news;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ienovo.list_ho.R;
import com.example.ienovo.list_ho.Show_Detils;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class ProductsAdapter_News extends RecyclerView.Adapter<ProductsAdapter_News.ProductViewHolder> {

public  static String text;

    int lastPosition=0;

    private static final int MY_SOCKET_TIMEOUT_MS = 400000;

    public  static String content;
    public  static int  id;

    private Context mCtx;
    private List<Element_News> productList;

    public ProductsAdapter_News(Context mCtx, List<Element_News> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.news_list, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ProductViewHolder holder, final int position) {
     final Element_News product = productList.get(position);

        if (position > lastPosition) {                                           //anim_recycler_item_show
            Animation animation = AnimationUtils.loadAnimation(mCtx, R.anim.swing_up_left);

            ((ProductViewHolder) holder).onclik.startAnimation(animation);


            lastPosition = position;
        }
        lastPosition = -1;

        Glide.with(mCtx)
                .load(R.drawable.ny_logo)
                .into(holder.imageView);
        holder.textViewTitle.setText(product.title());
        holder.textViewShortDesc.setText(product.getAbstrac_text());


        holder.onclik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent al = new Intent(mCtx, Show_Detils.class);
                mCtx.startActivity(al);
                ((Activity)mCtx).overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
                Animation animation = AnimationUtils.loadAnimation(mCtx, R.anim.swing_up_left);
                ((ProductViewHolder) holder).onclik.startAnimation(animation);



            }
        });
    }
    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle, textViewShortDesc;

        CircleImageView imageView;
       // ImageView fev;
ConstraintLayout onclik;

        public ProductViewHolder(View itemView) {
            super(itemView);
            textViewTitle = (TextView) itemView.findViewById(R.id.textView2);
            textViewShortDesc = (TextView) itemView.findViewById(R.id.textView3);
            imageView = (CircleImageView) itemView.findViewById(R.id.user_profile_photo);
            onclik=(ConstraintLayout)itemView.findViewById(R.id.constr);

        }




    }



}