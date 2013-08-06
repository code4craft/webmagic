package us.codecraft.webmagic.pipeline;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author code4crafter@gmail.com <br>
 * @date: 13-8-5 <br>
 * Time: 下午2:11 <br>
 */
public class LucenePipeline implements Pipeline {

    private Directory directory;

    private Analyzer analyzer;

    private IndexWriterConfig config;

    private void init() throws IOException {
        analyzer = new StandardAnalyzer(Version.LUCENE_44);
        directory = new RAMDirectory();
        config = new IndexWriterConfig(Version.LUCENE_44, analyzer);
    }

    public LucenePipeline() {
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Document> search(String fieldName, String value) throws IOException, ParseException {
        List<Document> documents = new ArrayList<Document>();
        DirectoryReader ireader = DirectoryReader.open(directory);
        IndexSearcher isearcher = new IndexSearcher(ireader);
        // Parse a simple query that searches for "text":
        QueryParser parser = new QueryParser(Version.LUCENE_44, fieldName, analyzer);
        Query query = parser.parse(value);
        ScoreDoc[] hits = isearcher.search(query, null, 1000).scoreDocs;
        // Iterate through the results:
        for (int i = 0; i < hits.length; i++) {
            Document hitDoc = isearcher.doc(hits[i].doc);
            documents.add(hitDoc);
        }
        ireader.close();
        return documents;
    }

    @Override
    public void process(ResultItems resultItems, Task task) {
        if (resultItems.isSkip()){
            return;
        }
        Document doc = new Document();
        Map<String,Object> all = resultItems.getAll();
        if (all==null){
            return;
        }
        for (Map.Entry<String, Object> objectEntry : all.entrySet()) {
            doc.add(new Field(objectEntry.getKey(), objectEntry.getValue().toString(), TextField.TYPE_STORED));
        }
        try {
            IndexWriter indexWriter = new IndexWriter(directory, config);
            indexWriter.addDocument(doc);
            indexWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
