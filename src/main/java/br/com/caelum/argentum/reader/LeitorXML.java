package br.com.caelum.argentum.reader;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;

import br.com.caelum.argentum.modelo.Negociacao;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.extended.GregorianCalendarConverter;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class LeitorXML {
	
	public List<Negociacao> carrega(InputStream inputStream) {
		XStream stream = new XStream(new DomDriver());
		stream.alias("negociacao", Negociacao.class);
		stream.alias("data", LocalDateTime.class);
		stream.registerLocalConverter(Negociacao.class, "data", new GregorianCalendarConverter()
//	        new com.thoughtworks.xstream.converters.basic.DateConverter("yyyy-MM-dd'T'HH:mm", new String[] {"dd/MM/yyyy HH:mm"},new GregorianCalendar().getTimeZone()){
//	          public boolean canConvert(Class type) {
//	                return type.equals(Date.class) || type.equals(Timestamp.class);
//	          }
//	          public String toString(Object obj) {
//	              return new SimpleDateFormat("yyyy-MM-dd HH:mm").format((Date) obj);
//	          }
//	        }
//			new LocalDateTimeConverter() {
//				public boolean canConvert(Class type) {
//					return type.equals(Date.class) || type.equals(Timestamp.class);
//				}
//				
//				public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
//					Calendar calendar = reader
//					
//				}
//				
//				public String toString(Object obj) {
//		              return new SimpleDateFormat("yyyy-MM-dd HH:mm").format((Date) obj);
//		          }
//			}
			
		);
		return (List<Negociacao>) stream.fromXML(inputStream);
	}
}
