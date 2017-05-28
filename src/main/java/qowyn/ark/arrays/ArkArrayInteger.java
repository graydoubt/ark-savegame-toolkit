package qowyn.ark.arrays;

import java.util.ArrayList;
import java.util.Set;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonNumber;

import qowyn.ark.ArkArchive;
import qowyn.ark.properties.PropertyArray;
import qowyn.ark.types.ArkName;

public class ArkArrayInteger extends ArrayList<Integer> implements ArkArray<Integer> {

  public static final ArkName TYPE_SIGNED = ArkName.constantPlain("IntProperty");

  public static final ArkName TYPE_UNSIGNED = ArkName.constantPlain("UInt32Property");

  private static final long serialVersionUID = 1L;

  public ArkArrayInteger() {}

  public ArkArrayInteger(ArkArchive archive, PropertyArray property) {
    int size = archive.getInt();

    for (int n = 0; n < size; n++) {
      add(archive.getInt());
    }
  }

  public ArkArrayInteger(JsonArray a, PropertyArray property) {
    a.getValuesAs(JsonNumber.class).forEach(n -> this.add(n.intValue()));
  }

  @Override
  public Class<Integer> getValueClass() {
    return Integer.class;
  }

  @Override
  public int calculateSize(boolean nameTable) {
    return Integer.BYTES + size() * Integer.BYTES;
  }

  @Override
  public JsonArray toJson() {
    JsonArrayBuilder jab = Json.createArrayBuilder();

    this.forEach(n -> jab.add(n));

    return jab.build();
  }

  @Override
  public void write(ArkArchive archive) {
    archive.putInt(size());

    this.forEach(archive::putInt);
  }

  @Override
  public void collectNames(Set<String> nameTable) {}

}
