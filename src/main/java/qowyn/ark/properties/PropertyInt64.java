package qowyn.ark.properties;

import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import qowyn.ark.ArkArchive;
import qowyn.ark.types.ArkName;

public class PropertyInt64 extends PropertyBase<Long> {

  public static final ArkName TYPE_SIGNED = ArkName.constantPlain("Int64Property");

  public static final ArkName TYPE_UNSIGNED = ArkName.constantPlain("UInt64Property");

  public PropertyInt64(String name, ArkName typeName, long value) {
    super(ArkName.from(name), typeName, 0, value);
  }

  public PropertyInt64(String name, ArkName typeName, int index, long value) {
    super(ArkName.from(name), typeName, index, value);
  }

  public PropertyInt64(ArkArchive archive, PropertyArgs args) {
    super(archive, args);
    value = archive.getLong();
  }

  public PropertyInt64(JsonObject o) {
    super(o);
    value = o.getJsonNumber("value").longValueExact();
  }

  @Override
  public Class<Long> getValueClass() {
    return Long.class;
  }

  @Override
  protected void serializeValue(JsonObjectBuilder job) {
    job.add("value", value);
  }

  @Override
  protected void writeValue(ArkArchive archive) {
    archive.putLong(value);
  }

  @Override
  public int calculateDataSize(boolean nameTable) {
    return Long.BYTES;
  }

}
