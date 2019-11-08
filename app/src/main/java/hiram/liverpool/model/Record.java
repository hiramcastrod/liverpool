package hiram.liverpool.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

 public class Record {
     @SerializedName("productDisplayName")
     @Expose
     public String productDisplayName;
     @SerializedName("listPrice")
     @Expose
     public String listPrice;
     @SerializedName("lgImage")
     @Expose
     public String lgImage;

     public Record() {
    }


 }
