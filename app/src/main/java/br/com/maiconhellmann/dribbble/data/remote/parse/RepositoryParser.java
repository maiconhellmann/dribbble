package br.com.maiconhellmann.dribbble.data.remote.parse;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import br.com.maiconhellmann.dribbble.data.model.Repository;

public class RepositoryParser implements JsonDeserializer<List<Repository>>
{
    @Override
    public List<Repository> deserialize(JsonElement je,
                                  Type type,
                                  JsonDeserializationContext jdc) throws JsonParseException{
        JsonElement content = je.getAsJsonObject().get("items");

        Type listType = new TypeToken<List<Repository>>(){}.getType();
        return new Gson().fromJson(content, listType);
    }
}
