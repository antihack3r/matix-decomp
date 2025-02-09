// 
// Decompiled by Procyon v0.6.0
// 

package optifine.json;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.List;
import java.io.IOException;
import java.io.StringReader;
import java.io.Reader;
import java.util.LinkedList;

public class JSONParser
{
    public static final int S_INIT = 0;
    public static final int S_IN_FINISHED_VALUE = 1;
    public static final int S_IN_OBJECT = 2;
    public static final int S_IN_ARRAY = 3;
    public static final int S_PASSED_PAIR_KEY = 4;
    public static final int S_IN_PAIR_VALUE = 5;
    public static final int S_END = 6;
    public static final int S_IN_ERROR = -1;
    private LinkedList handlerStatusStack;
    private Yylex lexer;
    private Yytoken token;
    private int status;
    
    public JSONParser() {
        this.lexer = new Yylex((Reader)null);
        this.token = null;
        this.status = 0;
    }
    
    private int peekStatus(final LinkedList statusStack) {
        if (statusStack.size() == 0) {
            return -1;
        }
        final Integer integer = statusStack.getFirst();
        return integer;
    }
    
    public void reset() {
        this.token = null;
        this.status = 0;
        this.handlerStatusStack = null;
    }
    
    public void reset(final Reader in) {
        this.lexer.yyreset(in);
        this.reset();
    }
    
    public int getPosition() {
        return this.lexer.getPosition();
    }
    
    public Object parse(final String s) throws ParseException {
        return this.parse(s, (ContainerFactory)null);
    }
    
    public Object parse(final String s, final ContainerFactory containerFactory) throws ParseException {
        final StringReader stringreader = new StringReader(s);
        try {
            return this.parse(stringreader, containerFactory);
        }
        catch (final IOException ioexception) {
            throw new ParseException(-1, 2, ioexception);
        }
    }
    
    public Object parse(final Reader in) throws IOException, ParseException {
        return this.parse(in, (ContainerFactory)null);
    }
    
    public Object parse(final Reader in, final ContainerFactory containerFactory) throws IOException, ParseException {
        this.reset(in);
        final LinkedList linkedlist = new LinkedList();
        final LinkedList linkedlist2 = new LinkedList();
        try {
            do {
                this.nextToken();
                Label_0915: {
                    switch (this.status) {
                        case -1: {
                            throw new ParseException(this.getPosition(), 1, this.token);
                        }
                        case 0: {
                            switch (this.token.type) {
                                case 0: {
                                    this.status = 1;
                                    linkedlist.addFirst(new Integer(this.status));
                                    linkedlist2.addFirst(this.token.value);
                                    break Label_0915;
                                }
                                case 1: {
                                    this.status = 2;
                                    linkedlist.addFirst(new Integer(this.status));
                                    linkedlist2.addFirst(this.createObjectContainer(containerFactory));
                                    break Label_0915;
                                }
                                default: {
                                    this.status = -1;
                                    break Label_0915;
                                }
                                case 3: {
                                    this.status = 3;
                                    linkedlist.addFirst(new Integer(this.status));
                                    linkedlist2.addFirst(this.createArrayContainer(containerFactory));
                                    break Label_0915;
                                }
                            }
                            break;
                        }
                        case 1: {
                            if (this.token.type == -1) {
                                return linkedlist2.removeFirst();
                            }
                            throw new ParseException(this.getPosition(), 1, this.token);
                        }
                        case 2: {
                            switch (this.token.type) {
                                case 0: {
                                    if (this.token.value instanceof String) {
                                        final String s3 = (String)this.token.value;
                                        linkedlist2.addFirst(s3);
                                        this.status = 4;
                                        linkedlist.addFirst(new Integer(this.status));
                                        break Label_0915;
                                    }
                                    this.status = -1;
                                    break Label_0915;
                                }
                                default: {
                                    this.status = -1;
                                    break Label_0915;
                                }
                                case 2:
                                    Label_0420: {
                                        if (linkedlist2.size() > 1) {
                                            linkedlist.removeFirst();
                                            linkedlist2.removeFirst();
                                            this.status = this.peekStatus(linkedlist);
                                            break Label_0420;
                                        }
                                        this.status = 1;
                                        break Label_0420;
                                    }
                                case 5: {
                                    break Label_0915;
                                }
                            }
                            break;
                        }
                        case 3: {
                            switch (this.token.type) {
                                case 0: {
                                    final List list3 = linkedlist2.getFirst();
                                    list3.add(this.token.value);
                                    break Label_0915;
                                }
                                case 1: {
                                    final List list4 = linkedlist2.getFirst();
                                    final Map map4 = this.createObjectContainer(containerFactory);
                                    list4.add(map4);
                                    this.status = 2;
                                    linkedlist.addFirst(new Integer(this.status));
                                    linkedlist2.addFirst(map4);
                                    break Label_0915;
                                }
                                default: {
                                    this.status = -1;
                                    break Label_0915;
                                }
                                case 3: {
                                    final List list5 = linkedlist2.getFirst();
                                    final List list6 = this.createArrayContainer(containerFactory);
                                    list5.add(list6);
                                    this.status = 3;
                                    linkedlist.addFirst(new Integer(this.status));
                                    linkedlist2.addFirst(list6);
                                    break Label_0915;
                                }
                                case 4:
                                    Label_0655: {
                                        if (linkedlist2.size() > 1) {
                                            linkedlist.removeFirst();
                                            linkedlist2.removeFirst();
                                            this.status = this.peekStatus(linkedlist);
                                            break Label_0655;
                                        }
                                        this.status = 1;
                                        break Label_0655;
                                    }
                                case 5: {
                                    break Label_0915;
                                }
                            }
                            break;
                        }
                        case 4: {
                            switch (this.token.type) {
                                default: {
                                    this.status = -1;
                                    break Label_0915;
                                }
                                case 6: {
                                    break Label_0915;
                                }
                                case 0: {
                                    linkedlist.removeFirst();
                                    final String s4 = linkedlist2.removeFirst();
                                    final Map map5 = linkedlist2.getFirst();
                                    map5.put(s4, this.token.value);
                                    this.status = this.peekStatus(linkedlist);
                                    break Label_0915;
                                }
                                case 1: {
                                    linkedlist.removeFirst();
                                    final String s5 = linkedlist2.removeFirst();
                                    final Map map6 = linkedlist2.getFirst();
                                    final Map map7 = this.createObjectContainer(containerFactory);
                                    map6.put(s5, map7);
                                    this.status = 2;
                                    linkedlist.addFirst(new Integer(this.status));
                                    linkedlist2.addFirst(map7);
                                    break Label_0915;
                                }
                                case 3: {
                                    linkedlist.removeFirst();
                                    final String s6 = linkedlist2.removeFirst();
                                    final Map map8 = linkedlist2.getFirst();
                                    final List list7 = this.createArrayContainer(containerFactory);
                                    map8.put(s6, list7);
                                    this.status = 3;
                                    linkedlist.addFirst(new Integer(this.status));
                                    linkedlist2.addFirst(list7);
                                    break Label_0915;
                                }
                            }
                            break;
                        }
                    }
                }
                if (this.status == -1) {
                    throw new ParseException(this.getPosition(), 1, this.token);
                }
            } while (this.token.type != -1);
            throw new ParseException(this.getPosition(), 1, this.token);
        }
        catch (final IOException ioexception) {
            throw ioexception;
        }
    }
    
    private void nextToken() throws ParseException, IOException {
        this.token = this.lexer.yylex();
        if (this.token == null) {
            this.token = new Yytoken(-1, null);
        }
    }
    
    private Map createObjectContainer(final ContainerFactory containerFactory) {
        if (containerFactory == null) {
            return new JSONObject();
        }
        final Map map = containerFactory.createObjectContainer();
        return (map == null) ? new JSONObject() : map;
    }
    
    private List createArrayContainer(final ContainerFactory containerFactory) {
        if (containerFactory == null) {
            return new JSONArray();
        }
        final List list = containerFactory.creatArrayContainer();
        return (list == null) ? new JSONArray() : list;
    }
    
    public void parse(final String s, final ContentHandler contentHandler) throws ParseException {
        this.parse(s, contentHandler, false);
    }
    
    public void parse(final String s, final ContentHandler contentHandler, final boolean isResume) throws ParseException {
        final StringReader stringreader = new StringReader(s);
        try {
            this.parse(stringreader, contentHandler, isResume);
        }
        catch (final IOException ioexception) {
            throw new ParseException(-1, 2, ioexception);
        }
    }
    
    public void parse(final Reader in, final ContentHandler contentHandler) throws IOException, ParseException {
        this.parse(in, contentHandler, false);
    }
    
    public void parse(final Reader in, final ContentHandler contentHandler, boolean isResume) throws IOException, ParseException {
        if (!isResume) {
            this.reset(in);
            this.handlerStatusStack = new LinkedList();
        }
        else if (this.handlerStatusStack == null) {
            isResume = false;
            this.reset(in);
            this.handlerStatusStack = new LinkedList();
        }
        final LinkedList linkedlist = this.handlerStatusStack;
        try {
            do {
                Label_0917: {
                    switch (this.status) {
                        case -1: {
                            throw new ParseException(this.getPosition(), 1, this.token);
                        }
                        case 0: {
                            contentHandler.startJSON();
                            this.nextToken();
                            switch (this.token.type) {
                                case 0: {
                                    this.status = 1;
                                    linkedlist.addFirst(new Integer(this.status));
                                    if (!contentHandler.primitive(this.token.value)) {
                                        return;
                                    }
                                    break Label_0917;
                                }
                                case 1: {
                                    this.status = 2;
                                    linkedlist.addFirst(new Integer(this.status));
                                    if (!contentHandler.startObject()) {
                                        return;
                                    }
                                    break Label_0917;
                                }
                                default: {
                                    this.status = -1;
                                    break Label_0917;
                                }
                                case 3: {
                                    this.status = 3;
                                    linkedlist.addFirst(new Integer(this.status));
                                    if (!contentHandler.startArray()) {
                                        return;
                                    }
                                    break Label_0917;
                                }
                            }
                            break;
                        }
                        case 1: {
                            this.nextToken();
                            if (this.token.type == -1) {
                                contentHandler.endJSON();
                                this.status = 6;
                                return;
                            }
                            this.status = -1;
                            throw new ParseException(this.getPosition(), 1, this.token);
                        }
                        case 2: {
                            this.nextToken();
                            switch (this.token.type) {
                                case 0: {
                                    if (!(this.token.value instanceof String)) {
                                        this.status = -1;
                                        break Label_0917;
                                    }
                                    final String s = (String)this.token.value;
                                    this.status = 4;
                                    linkedlist.addFirst(new Integer(this.status));
                                    if (!contentHandler.startObjectEntry(s)) {
                                        return;
                                    }
                                    break Label_0917;
                                }
                                default: {
                                    this.status = -1;
                                    break Label_0917;
                                }
                                case 2: {
                                    if (linkedlist.size() > 1) {
                                        linkedlist.removeFirst();
                                        this.status = this.peekStatus(linkedlist);
                                    }
                                    else {
                                        this.status = 1;
                                    }
                                    if (!contentHandler.endObject()) {
                                        return;
                                    }
                                }
                                case 5: {
                                    break Label_0917;
                                }
                            }
                            break;
                        }
                        case 3: {
                            this.nextToken();
                            switch (this.token.type) {
                                case 0: {
                                    if (!contentHandler.primitive(this.token.value)) {
                                        return;
                                    }
                                    break Label_0917;
                                }
                                case 1: {
                                    this.status = 2;
                                    linkedlist.addFirst(new Integer(this.status));
                                    if (!contentHandler.startObject()) {
                                        return;
                                    }
                                    break Label_0917;
                                }
                                default: {
                                    this.status = -1;
                                    break Label_0917;
                                }
                                case 3: {
                                    this.status = 3;
                                    linkedlist.addFirst(new Integer(this.status));
                                    if (!contentHandler.startArray()) {
                                        return;
                                    }
                                    break Label_0917;
                                }
                                case 4: {
                                    if (linkedlist.size() > 1) {
                                        linkedlist.removeFirst();
                                        this.status = this.peekStatus(linkedlist);
                                    }
                                    else {
                                        this.status = 1;
                                    }
                                    if (!contentHandler.endArray()) {
                                        return;
                                    }
                                }
                                case 5: {
                                    break Label_0917;
                                }
                            }
                            break;
                        }
                        case 4: {
                            this.nextToken();
                            switch (this.token.type) {
                                case 0: {
                                    linkedlist.removeFirst();
                                    this.status = this.peekStatus(linkedlist);
                                    if (!contentHandler.primitive(this.token.value)) {
                                        return;
                                    }
                                    if (!contentHandler.endObjectEntry()) {
                                        return;
                                    }
                                    break Label_0917;
                                }
                                case 1: {
                                    linkedlist.removeFirst();
                                    linkedlist.addFirst(new Integer(5));
                                    this.status = 2;
                                    linkedlist.addFirst(new Integer(this.status));
                                    if (!contentHandler.startObject()) {
                                        return;
                                    }
                                    break Label_0917;
                                }
                                default: {
                                    this.status = -1;
                                    break Label_0917;
                                }
                                case 3: {
                                    linkedlist.removeFirst();
                                    linkedlist.addFirst(new Integer(5));
                                    this.status = 3;
                                    linkedlist.addFirst(new Integer(this.status));
                                    if (!contentHandler.startArray()) {
                                        return;
                                    }
                                }
                                case 6: {
                                    break Label_0917;
                                }
                            }
                            break;
                        }
                        case 5: {
                            linkedlist.removeFirst();
                            this.status = this.peekStatus(linkedlist);
                            if (!contentHandler.endObjectEntry()) {
                                return;
                            }
                            break;
                        }
                        case 6: {
                            return;
                        }
                    }
                }
                if (this.status == -1) {
                    throw new ParseException(this.getPosition(), 1, this.token);
                }
            } while (this.token.type != -1);
        }
        catch (final IOException ioexception) {
            this.status = -1;
            throw ioexception;
        }
        catch (final ParseException parseexception) {
            this.status = -1;
            throw parseexception;
        }
        catch (final RuntimeException runtimeexception) {
            this.status = -1;
            throw runtimeexception;
        }
        catch (final Error error) {
            this.status = -1;
            throw error;
        }
        this.status = -1;
        throw new ParseException(this.getPosition(), 1, this.token);
    }
    
    public static Date parseDate(String input) {
        if (input == null) {
            return null;
        }
        final SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz");
        if (input.endsWith("Z")) {
            input = input.substring(0, input.length() - 1) + "GMT-00:00";
        }
        else {
            final int i = 6;
            final String s = input.substring(0, input.length() - i);
            final String s2 = input.substring(input.length() - i, input.length());
            input = s + "GMT" + s2;
        }
        try {
            return simpledateformat.parse(input);
        }
        catch (final java.text.ParseException parseexception) {
            System.out.println("Error parsing date: " + input);
            System.out.println(parseexception.getClass().getName() + ": " + parseexception.getMessage());
            return null;
        }
    }
}
