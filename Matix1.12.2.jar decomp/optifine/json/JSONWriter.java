// 
// Decompiled by Procyon v0.6.0
// 

package optifine.json;

import java.util.Iterator;
import java.util.Set;
import java.io.IOException;
import java.io.Writer;

public class JSONWriter
{
    private Writer writer;
    private int indentStep;
    private int indent;
    
    public JSONWriter(final Writer writer) {
        this.writer = null;
        this.indentStep = 2;
        this.indent = 0;
        this.writer = writer;
    }
    
    public JSONWriter(final Writer writer, final int indentStep) {
        this.writer = null;
        this.indentStep = 2;
        this.indent = 0;
        this.writer = writer;
        this.indentStep = indentStep;
    }
    
    public JSONWriter(final Writer writer, final int indentStep, final int indent) {
        this.writer = null;
        this.indentStep = 2;
        this.indent = 0;
        this.writer = writer;
        this.indentStep = indentStep;
        this.indent = indent;
    }
    
    public void writeObject(final Object obj) throws IOException {
        if (obj instanceof JSONObject) {
            final JSONObject jsonobject = (JSONObject)obj;
            this.writeJsonObject(jsonobject);
        }
        else if (obj instanceof JSONArray) {
            final JSONArray jsonarray = (JSONArray)obj;
            this.writeJsonArray(jsonarray);
        }
        else {
            this.writer.write(JSONValue.toJSONString(obj));
        }
    }
    
    private void writeJsonArray(final JSONArray jArr) throws IOException {
        this.writeLine("[");
        this.indentAdd();
        for (int i = jArr.size(), j = 0; j < i; ++j) {
            final Object object = jArr.get(j);
            this.writeIndent();
            this.writeObject(object);
            if (j < jArr.size() - 1) {
                this.write(",");
            }
            this.writeLine("");
        }
        this.indentRemove();
        this.writeIndent();
        this.writer.write("]");
    }
    
    private void writeJsonObject(final JSONObject jObj) throws IOException {
        this.writeLine("{");
        this.indentAdd();
        final Set<String> set = jObj.keySet();
        final int i = set.size();
        int j = 0;
        for (final String s : set) {
            final Object object = jObj.get(s);
            this.writeIndent();
            this.writer.write(JSONValue.toJSONString(s));
            this.writer.write(": ");
            this.writeObject(object);
            if (++j < i) {
                this.writeLine(",");
            }
            else {
                this.writeLine("");
            }
        }
        this.indentRemove();
        this.writeIndent();
        this.writer.write("}");
    }
    
    private void writeLine(final String str) throws IOException {
        this.writer.write(str);
        this.writer.write("\n");
    }
    
    private void write(final String str) throws IOException {
        this.writer.write(str);
    }
    
    private void writeIndent() throws IOException {
        for (int i = 0; i < this.indent; ++i) {
            this.writer.write(32);
        }
    }
    
    private void indentAdd() {
        this.indent += this.indentStep;
    }
    
    private void indentRemove() {
        this.indent -= this.indentStep;
    }
}
