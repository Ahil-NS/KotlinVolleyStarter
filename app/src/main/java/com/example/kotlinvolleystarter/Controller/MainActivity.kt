package com.example.kotlinvolleystarter.Controller

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.kotlinvolleystarter.Adapter.RecipeListAdapter
import com.example.kotlinvolleystarter.Model.Recipe
import com.example.kotlinvolleystarter.R
import com.example.kotlinvolleystarter.Utils.MAIN_URL
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    var volleyRequest: RequestQueue? = null
    var recipeList: ArrayList<Recipe>? = null
    var recipeAdapter: RecipeListAdapter? = null
    var layoutManager: RecyclerView.LayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        recipeList = ArrayList<Recipe>()

        volleyRequest = Volley.newRequestQueue(this)

        getRecipe(MAIN_URL)


    }

    fun getRecipe(url: String) {
        val recipeRequest = JsonObjectRequest(
            Request.Method.GET,
            url, Response.Listener {
                    response: JSONObject ->
                try {

                    val resultArray = response.getJSONArray("results")

                    for (i in 0..resultArray.length() - 1) {
                        var recipeObj = resultArray.getJSONObject(i)

                        var title = recipeObj.getString("title")
                        var link = recipeObj.getString("href")
                        var thumbnail = recipeObj.getString("thumbnail")

                        var recipe = Recipe()
                        recipe.title = title
                        recipe.link = link
                        recipe.thumbnail = thumbnail


                        recipeList!!.add(recipe)

                        recipeAdapter = RecipeListAdapter(recipeList!!, this)
                        layoutManager = LinearLayoutManager(this)

                        //setup list/recyclerview
                        recyclerView.layoutManager = layoutManager
                        recyclerView.adapter = recipeAdapter

                    }

                    recipeAdapter!!.notifyDataSetChanged()


                }catch (e: JSONException) { e.printStackTrace()}

            },
            Response.ErrorListener {
                    error: VolleyError? ->
                try {
                    Log.d("Error:", error.toString())

                }catch (e: JSONException){e.printStackTrace()}
            })


        volleyRequest!!.add(recipeRequest)
    }

}
