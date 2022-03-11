package com.wohr.easymock.obj;

import org.easymock.EasyMockRule;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.*;

import java.util.NoSuchElementException;

import static org.easymock.EasyMock.*;

public class BeerReaderAnnotatedWithRuleUnitTest {

	@Rule
	public EasyMockRule mockRule = new EasyMockRule(this);

	@Mock
	ArticleReader mockArticleReader;

	@Mock
	IArticleWriter mockArticleWriter;

	@TestSubject
    BeerReader baeldungReader = new BeerReader();

    @Test
    public void givenBaeldungReader_whenReadNext_thenNextArticleRead() {
        expect(mockArticleReader.next()).andReturn(null);
        replay(mockArticleReader);
        baeldungReader.readNext();
        verify(mockArticleReader);
    }

    @Test
    public void givenBaeldungReader_whenWrite_thenWriterCalled() {
        expect(mockArticleWriter.write("title", "content")).andReturn(null);
        expect(mockArticleWriter.write("title", "content")).andReturn("teste");
        replay(mockArticleWriter);
        baeldungReader.write("title", "content");
//        baeldungReader.write("title", "content");
        verify(mockArticleWriter);
    }

    @Test
    public void givenArticlesInReader_whenReadTillEnd_thenThrowException() {
        expect(mockArticleReader.next())
          .andReturn(null)
          .times(2)
          .andThrow(new NoSuchElementException());
        replay(mockArticleReader);
        try {
            for (int i = 0; i < 3; i++) {
                baeldungReader.readNext();
            }
        } catch (Exception ignored) {
        }
        verify(mockArticleReader);
    }

}