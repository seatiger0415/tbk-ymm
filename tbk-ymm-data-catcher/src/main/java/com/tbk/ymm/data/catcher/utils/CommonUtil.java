package com.tbk.ymm.data.catcher.utils;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.regex.Pattern;

public class CommonUtil {

	public static final Pattern NUMBER = Pattern.compile("[0-9]+");

	public static final Random RANDOM = new Random(System.currentTimeMillis());

	public static String relativeUrlToAbsolute(final String refferedUrl, final String href) {
		return null;
	}

	public static String str2unicode(final String s) {
		final StringBuilder uStr = new StringBuilder("");
		if (s == null) {
			return "";
		}
		final int size = s.length();
		for (int i = 0; i < size; i++) {
			final int iValue = s.codePointAt(i);
			if (iValue < 16) {
				uStr.append("\\u000").append(Integer.toHexString(iValue));
			} else if (iValue < 256) {
				uStr.append("\\u00").append(Integer.toHexString(iValue));
			} else if (iValue < 4096) {
				uStr.append("\\u0").append(Integer.toHexString(iValue));
			} else {
				uStr.append("\\u").append(Integer.toHexString(iValue));
			}
		}
		return uStr.toString();
	}

	public static int strlen(String s) {
		if (s == null) {
			return 0;
		}
		int len = 0;
		s = s.trim();
		for (int i = 0; i < s.length(); i++) {
			if (s.codePointAt(i) > 255) {
				len += 2;
			} else {
				++len;
			}
		}
		return len;
	}

	public static String getExtention(final String fileName) {
		final int pos = fileName.lastIndexOf(".");
		return fileName.substring(pos).toLowerCase();
	}

	public static File createTmpFile() {
		return new File("/data/tmp/uptmp/" + System.currentTimeMillis() + CommonUtil.randomString(6));
	}

	private static int rand(final int lo, final int hi) {
		final int n = hi - lo + 1;
		int i = RANDOM.nextInt() % n;
		if (i < 0) {
			i = -i;
		}
		return lo + i;
	}

	public static String randomString(final int len) {
		return randomstring(len, len);
	}

	public static String randomstring(final int lo, final int hi) {
		final int len = rand(lo, hi);
		final byte b[] = new byte[len];
		for (int i = 0; i < len; i++) {
			int mux = RANDOM.nextInt() % 3;
			if (mux < 0) {
				mux = -mux;
			}
			switch (mux) {
				case 0 :
					b[i] = (byte) rand('a', 'z');
					break;
				case 1 :
					b[i] = (byte) rand('A', 'Z');
					break;
				case 2 :
					b[i] = (byte) rand('0', '9');
					break;
			}
		}
		return new String(b, 0, len);
	}

	private static char ch[] = {//
	'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',//
			'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',//
			'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',//
			'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',//
			'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',//
			'0', '1'};

	public static String getPasswordString(final int length) {
		if (length > 0) {
			final char[] result = new char[length];
			int index = 0, rand = RANDOM.nextInt();
			for (int i = 0; i < length % 5; i++) {
				result[index++] = ch[(byte) rand & 63];
				rand >>= 6;
			}
			for (int i = length / 5; i > 0; i--) {
				rand = RANDOM.nextInt();
				for (int j = 0; j < 5; j++) {
					result[index++] = ch[(byte) rand & 63];
					rand >>= 6;
				}
			}
			return new String(result, 0, length);
		} else if (length == 0) {
			return "";
		} else {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * 将一个数组转换为字符串连接
	 * 
	 * @param array
	 *            必须是数组
	 * @return
	 */
	@SuppressWarnings({"rawtypes"})
	public static String join(Object array) {
		if (array != null) {
			if (array instanceof Collection) {
				array = ((Collection) array).toArray();
			}

			final StringBuilder sb = new StringBuilder();
			final int len = Array.getLength(array);
			for (int index = 0; index < len; index++) {
				if (index != 0) {
					sb.append(",");
				}
				sb.append(Array.get(array, index));
			}
			return sb.toString();
		} else {
			return "";
		}
	}

	/**
	 * 用Joiner吧.
	 * 
	 * @param <T>
	 * @param o
	 * @return
	 */
	public static <T> String join(final Collection<T> o) {
		if (o != null) {
			final StringBuilder sb = new StringBuilder();
			boolean s = false;
			for (final T i : o) {
				if (s) {
					sb.append(",");
				} else {
					s = true;
				}
				sb.append(i);
			}
			return sb.toString();
		} else {
			return "";
		}
	}

	/**
	 * @param string
	 * @param delimiter
	 * @return
	 */
	public static Integer[] splitToInteger(final String string, final String delimiter) {
		final String[] source = string.split(delimiter);
		if (source != null) {
			final int length = source.length;
			final Integer[] result = new Integer[length];
			for (int i = 0; i < length; i++) {
				result[i] = Integer.valueOf(source[i]);
			}
			return result;
		}
		return null;
	}

	public static int[] splitToInt(final String string, final String delimiter) {
		final String[] source = string.split(delimiter);
		if (source != null) {
			final int length = source.length;
			final int[] result = new int[length];
			for (int i = 0; i < length; i++) {
				result[i] = Integer.valueOf(source[i]);
			}
			return result;
		}
		return null;
	}

	public static List<Integer> splitToIntegerList(final String string, final String delimiter) {
		if (string != null && !string.equals("")) {
			final String[] source = string.split(delimiter);
			if (source != null) {
				final int length = source.length;
				final List<Integer> result = new ArrayList<Integer>(length);
				for (final String s : source) {
					result.add(Integer.valueOf(s));
				}
				return CommonUtil.checkToDefaultValue(result);
			}
		}
		return Collections.emptyList();
	}

	public static Set<Integer> splitToIntegerSet(final String string, final String delimiter) {
		if (string != null && !string.equals("")) {
			final String[] source = string.split(delimiter);
			if (source != null) {
				final int length = source.length;
				final Set<Integer> result = new HashSet<Integer>(length);
				for (final String s : source) {
					result.add(Integer.valueOf(s));
				}
				return CommonUtil.checkToDefaultValue(result);
			}
		}
		return Collections.emptySet();
	}

	/**
	 * 检测参数,若参数为null,则返回默认值
	 * 
	 * @param n
	 * @param defaultValue
	 *            指定的默认值
	 * @return
	 */
	public static <T> T checkToDefaultValue(final T n, final T defaultValue) {
		return n == null ? defaultValue : n;
	}

	@SuppressWarnings("unchecked")
	public static <T extends Collection<?>> T checkToDefaultValue(T collection) {
		if (collection == null) {
			collection = (T) Collections.emptyList();
		}
		return collection;
	}

	@SuppressWarnings("unchecked")
	public static <T extends List<?>> T checkToDefaultValue(T collection) {
		if (collection == null) {
			collection = (T) Collections.emptyList();
		}
		return collection;
	}

	@SuppressWarnings("unchecked")
	public static <T extends Set<?>> T checkToDefaultValue(T collection) {
		if (collection == null) {
			collection = (T) Collections.emptySet();
		}
		return collection;
	}

	@SuppressWarnings("unchecked")
	public static <T extends Map<?, ?>> T checkToDefaultValue(T collection) {
		if (collection == null) {
			collection = (T) Collections.emptyMap();
		}
		return collection;
	}

	public static String checkToDefaultValue(final String n) {
		return checkToDefaultValue(n, "");
	}

	public static boolean maskTest(final int source, final int mask) {
		return (source & mask) == mask;
	}

	public static boolean maskTest(final long source, final long mask) {
		return (source & mask) == mask;
	}

	public static boolean maskTaskByBit(final long source, final int bit) {
		// TODO 还没测试
		final long mask = 1 << bit - 1;
		return maskTest(source, mask);
	}

	/**
	 * 用Joiner吧.
	 * 
	 * @param <T>
	 * @param o
	 * @return
	 */
	/*
	 * public static <T> String join(final Collection<T> o, final KeyExtractable<?, T> keyExtractable) {
	 * if (o != null) {
	 * final StringBuilder sb = new StringBuilder();
	 * boolean s = false;
	 * for (final T i : o) {
	 * if (s) {
	 * sb.append(",");
	 * } else {
	 * s = true;
	 * }
	 * sb.append(keyExtractable.extract(i));
	 * }
	 * return sb.toString();
	 * } else {
	 * return "";
	 * }
	 * }
	 */
	/**
	 * 发送手机短信
	 * 
	 * @param phones
	 * @param message
	 */
	/*
	 * public static void sendSMS(String message, final String... phones) {
	 * if (phones != null) {
	 * try {
	 * message = URLEncoder.encode(message, "GBK");
	 * } catch (final UnsupportedEncodingException e) {
	 * e.printStackTrace();
	 * }
	 * for (final String phone : phones) {
	 * final String url = "http://10.22.198.81:2000/receiver?number=" + phone + "&message=" + message;
	 * VipLog.sysout.debug(url);
	 * HttpUtils.get(url, "GBK");
	 * }
	 * }
	 * }
	 */

	/**
	 * 发送电子邮件
	 * 
	 * @param message
	 * @param title
	 * @param emails
	 */
	/*
	 * public static void sendMail(String message, final String title, final String... emails) {
	 * try {
	 * if (VerifyUtil.isEmpty(message)) {
	 * message = "EMPTY BODY"; // 如果邮件内容为空，会被认为是SPAM，所以还是写一句吧
	 * }
	 * final Authenticator auth = new Authenticator() {
	 * 
	 * @Override
	 * public PasswordAuthentication getPasswordAuthentication() {
	 * final String username = "opivas";
	 * final String pwd = "123456";
	 * return new PasswordAuthentication(username, pwd);
	 * }
	 * };
	 * final Properties mailProps = new Properties();
	 * mailProps.put("mail.smtp.user", "opivas@163.com");
	 * mailProps.put("mail.smtp.host", "smtp.163.com");
	 * mailProps.put("mail.smtp.auth", "true");
	 * mailProps.put("username", "opivas");
	 * mailProps.put("password", "123456");
	 * 
	 * final Session mailSession = Session.getDefaultInstance(mailProps, auth);
	 * final MimeMessage mimeMessage = new MimeMessage(mailSession);
	 * final Address[] emailAddresses = new Address[emails.length];
	 * int i = 0;
	 * for (final String email : emails) {
	 * emailAddresses[i++] = new InternetAddress(email);
	 * }
	 * mimeMessage.setFrom(new InternetAddress("opivas@163.com"));
	 * mimeMessage.setRecipients(Message.RecipientType.TO, emailAddresses);
	 * mimeMessage.setSubject(title);
	 * final MimeMultipart multi = new MimeMultipart();
	 * final BodyPart textBodyPart = new MimeBodyPart();
	 * textBodyPart.setText(message);
	 * multi.addBodyPart(textBodyPart);
	 * mimeMessage.setContent(multi);
	 * mimeMessage.saveChanges();
	 * Transport.send(mimeMessage);
	 * } catch (final Throwable e) {
	 * e.printStackTrace();
	 * }
	 * }
	 *//**
	 * 发送电子邮件html
	 * 
	 * @param message
	 * @param title
	 * @param emails
	 */
	/*
	 * public static void sendMailHtml(String message, final String title, final String... emails) {
	 * try {
	 * if (VerifyUtil.isEmpty(message)) {
	 * message = "EMPTY BODY"; // 如果邮件内容为空，会被认为是SPAM，所以还是写一句吧
	 * }
	 * final Authenticator auth = new Authenticator() {
	 * 
	 * @Override
	 * public PasswordAuthentication getPasswordAuthentication() {
	 * final String username = "opivas";
	 * final String pwd = "123456";
	 * return new PasswordAuthentication(username, pwd);
	 * }
	 * };
	 * final Properties mailProps = new Properties();
	 * mailProps.put("mail.smtp.user", "opivas@163.com");
	 * mailProps.put("mail.smtp.host", "smtp.163.com");
	 * mailProps.put("mail.smtp.auth", "true");
	 * mailProps.put("username", "opivas");
	 * mailProps.put("password", "123456");
	 * 
	 * final Session mailSession = Session.getDefaultInstance(mailProps, auth);
	 * final MimeMessage mimeMessage = new MimeMessage(mailSession);
	 * final Address[] emailAddresses = new Address[emails.length];
	 * int i = 0;
	 * for (final String email : emails) {
	 * emailAddresses[i++] = new InternetAddress(email);
	 * }
	 * mimeMessage.setFrom(new InternetAddress("opivas@163.com"));
	 * mimeMessage.setRecipients(Message.RecipientType.TO, emailAddresses);
	 * mimeMessage.setSubject(title);
	 * final MimeMultipart multi = new MimeMultipart();
	 * final BodyPart textBodyPart = new MimeBodyPart();
	 * textBodyPart.setContent(message, "text/html;charset=gb2312");//给BodyPart对象设置内容和格式/编码方式
	 * multi.addBodyPart(textBodyPart);
	 * mimeMessage.setContent(multi);
	 * mimeMessage.saveChanges();
	 * Transport.send(mimeMessage);
	 * } catch (final Throwable e) {
	 * e.printStackTrace();
	 * }
	 * }
	 *//**
	 * 发送带有附件的电子邮件
	 * 
	 * @param message
	 * @param title
	 * @param emails
	 */
	/*
	 * public static void sendMailWithAttachment(String message, final String title, final Collection<String> fileNames,
	 * final String... emails) {
	 * try {
	 * if (VerifyUtil.isEmpty(message)) {
	 * message = "EMPTY BODY"; // 如果邮件内容为空，会被认为是SPAM，所以还是写一句吧
	 * }
	 * final Authenticator auth = new Authenticator() {
	 * 
	 * @Override
	 * public PasswordAuthentication getPasswordAuthentication() {
	 * final String username = "opivas";
	 * final String pwd = "123456";
	 * return new PasswordAuthentication(username, pwd);
	 * }
	 * };
	 * final Properties mailProps = new Properties();
	 * mailProps.put("mail.smtp.user", "opivas@163.com");
	 * mailProps.put("mail.smtp.host", "smtp.163.com");
	 * mailProps.put("mail.smtp.auth", "true");
	 * mailProps.put("username", "opivas");
	 * mailProps.put("password", "123456");
	 * 
	 * final Session mailSession = Session.getDefaultInstance(mailProps, auth);
	 * final MimeMessage mimeMessage = new MimeMessage(mailSession);
	 * final Address[] emailAddresses = new Address[emails.length];
	 * int i = 0;
	 * for (final String email : emails) {
	 * emailAddresses[i++] = new InternetAddress(email);
	 * }
	 * mimeMessage.setFrom(new InternetAddress("opivas@163.com"));
	 * mimeMessage.setRecipients(Message.RecipientType.TO, emailAddresses);
	 * mimeMessage.setSubject(title);
	 * final MimeMultipart multi = new MimeMultipart();
	 * final BodyPart textBodyPart = new MimeBodyPart();
	 * textBodyPart.setContent(message, "text/html;charset=gb2312");//给BodyPart对象设置内容和格式/编码方式
	 * if (fileNames != null) {
	 * for (final String fileName : fileNames) {
	 * try {
	 * final BodyPart attachment = new MimeBodyPart();
	 * final FileDataSource fds = new FileDataSource(fileName); //得到数据源
	 * attachment.setDataHandler(new DataHandler(fds)); //得到附件本身并至入BodyPart
	 * attachment.setFileName(fds.getName()); //得到文件名同样至入BodyPart
	 * multi.addBodyPart(attachment);
	 * } catch (final Throwable e) {
	 * e.printStackTrace();
	 * }
	 * }
	 * }
	 * multi.addBodyPart(textBodyPart);
	 * mimeMessage.setContent(multi);
	 * mimeMessage.saveChanges();
	 * Transport.send(mimeMessage);
	 * } catch (final Throwable e) {
	 * e.printStackTrace();
	 * }
	 * }
	 *//**
	 * 加密邮件地址
	 * 
	 * @param fmail
	 *            邮件地址明文
	 * @return 邮件地址密文
	 */
	/*
	 * public static String getUserMailByStar(final String fmail) {
	 * if (fmail == null || fmail.length() < 1) {
	 * return null;
	 * }
	 * final int pos = fmail.indexOf('@');
	 * final StringBuffer tmp = new StringBuffer();
	 * for (int i = 0; i < fmail.length(); i++) {
	 * if (i < 2 || i > pos) {
	 * tmp.append(fmail.charAt(i));
	 * } else {
	 * if (i == pos) {
	 * tmp.append("@");
	 * } else if (i == pos - 1) {
	 * tmp.append(fmail.charAt(i));
	 * } else {
	 * tmp.append("*");
	 * }
	 * }
	 * }
	 * return tmp.toString();
	 * }
	 */

	public static void main(final String[] arg) {
		// CommonUtil.sendMail("不要慌", "测试邮件", Constant.adminEmails());
		// CommonUtil.sendMail("不要慌", "测试邮件", Constant.templateEmails());
		// CommonUtil.sendSMS("测试短信，不要慌", Constant.adminPhones());
		// System.out.println("@@@@@@" + Md5Util.md5("2011-07-19mv_interface_!@#$%"));
	}
}
