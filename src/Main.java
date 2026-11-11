
import java.awt.*;
import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.basic.BasicButtonUI;

public class Main extends JFrame {

    // ================= THEME COLORS =================

    private final Color NAVY = new Color(12, 27, 49);
    private final Color NAVY2 = new Color(18, 42, 72);
    private final Color PANEL = new Color(24, 49, 79);

    private final Color BUTTON_BLUE = new Color(35, 90, 190);
    private final Color BUTTON_HOVER = new Color(65, 125, 230);

    private final Color LIGHT = new Color(235, 242, 252);
    private final Color MUTED = new Color(165, 183, 207);
    private final Color GREEN = new Color(74, 210, 151);

    private CardLayout cardLayout;
    private JPanel contentCards;
    private JPanel chatMessages;
    private JScrollPane chatScroll;
    private JTextField messageField;
    private ImageIcon logoIcon;

    // ================= EXAM DETAILS =================

    private final String[][] exams = {
        {"25 Nov 2026", "Discrete Mathematics", "MA25C08", "2026-11-25"},
        {"27 Nov 2026", "Data Structures", "CS25C08", "2026-11-27"},
        {"30 Nov 2026", "Java Programming", "CS25C09", "2026-11-30"},
        {"02 Dec 2026", "Object Oriented Software Engineering", "CS25C10", "2026-12-02"},
        {"04 Dec 2026", "Operating Systems", "CS25C11", "2026-12-04"}
    };

    // ================= CONSTRUCTOR =================

    public Main() {

        super("SMIT Chatbot Assistant");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1180, 780);
        setMinimumSize(new Dimension(900, 620));
        setLocationRelativeTo(null);

        loadLogo();

        getContentPane().setBackground(NAVY);
        setLayout(new BorderLayout());

        add(createHeader(), BorderLayout.NORTH);
        add(createSidebar(), BorderLayout.WEST);

        cardLayout = new CardLayout();

        contentCards = new JPanel(cardLayout);
        contentCards.setBackground(NAVY);
        contentCards.setBorder(
            new EmptyBorder(18, 18, 18, 18)
        );

        contentCards.add(createDashboard(), "dashboard");
        contentCards.add(createChatPage(), "chat");
        contentCards.add(createTimetablePage(), "timetable");
        contentCards.add(createExamPage(), "exams");
        contentCards.add(createSubjectsPage(), "subjects");

        add(contentCards, BorderLayout.CENTER);
        add(createFooter(), BorderLayout.SOUTH);

        showPage("dashboard");
    }

    // ================= LOGO =================

    private void loadLogo() {

        try {

            File local = new File("assets/SMIT-LOGO.png");

            if (local.exists()) {

                Image image = ImageIO.read(local);

                logoIcon = new ImageIcon(
                    image.getScaledInstance(
                        68, 68, Image.SCALE_SMOOTH
                    )
                );

                return;
            }

            URL url = new URL(
                "https://smit.edu.in/wp-content/uploads/2026/02/SMIT-LOGO.png"
            );

            Image image = ImageIO.read(url);

            logoIcon = new ImageIcon(
                image.getScaledInstance(
                    68, 68, Image.SCALE_SMOOTH
                )
            );

        } catch (Exception ex) {

            logoIcon = null;

            System.out.println(
                "SMIT logo could not be loaded. Put it at assets/SMIT-LOGO.png"
            );
        }
    }

    // ================= HEADER =================

    private JPanel createHeader() {

        JPanel header = new JPanel(
            new BorderLayout(16, 0)
        );

        header.setBackground(NAVY2);
        header.setBorder(
            new EmptyBorder(14, 22, 14, 22)
        );

        JLabel logo = new JLabel();

        if (logoIcon != null) {

            logo.setIcon(logoIcon);

        } else {

            logo.setText("SMIT");
            logo.setForeground(Color.WHITE);
            logo.setFont(
                new Font("SansSerif", Font.BOLD, 22)
            );
        }

        JPanel titles = new JPanel();

        titles.setOpaque(false);

        titles.setLayout(
            new BoxLayout(titles, BoxLayout.Y_AXIS)
        );

        JLabel title = new JLabel(
            "SMIT Chatbot Assistant"
        );

        title.setForeground(Color.WHITE);
        title.setFont(
            new Font("SansSerif", Font.BOLD, 25)
        );

        JLabel college = new JLabel(
            "Sri Muthukumaran Institute of Technology"
        );

        college.setForeground(LIGHT);
        college.setFont(
            new Font("SansSerif", Font.PLAIN, 14)
        );

        JLabel status = new JLabel(
            "●  CSE - B Section   |   II Year   |   ONLINE"
        );

        status.setForeground(GREEN);
        status.setFont(
            new Font("SansSerif", Font.BOLD, 12)
        );

        titles.add(title);
        titles.add(Box.createVerticalStrut(4));
        titles.add(college);
        titles.add(Box.createVerticalStrut(7));
        titles.add(status);

        header.add(logo, BorderLayout.WEST);
        header.add(titles, BorderLayout.CENTER);

        return header;
    }

    // ================= SIDEBAR =================

    private JPanel createSidebar() {

        JPanel side = new JPanel();

        side.setBackground(new Color(9, 21, 39));
        side.setPreferredSize(
            new Dimension(205, 100)
        );

        side.setBorder(
            new EmptyBorder(18, 12, 18, 12)
        );

        side.setLayout(
            new BoxLayout(side, BoxLayout.Y_AXIS)
        );

        JLabel menu = new JLabel("  MENU");

        menu.setForeground(MUTED);
        menu.setFont(
            new Font("SansSerif", Font.BOLD, 11)
        );

        menu.setAlignmentX(Component.LEFT_ALIGNMENT);

        side.add(menu);
        side.add(Box.createVerticalStrut(14));

        addNavButton(side, "⌂   Dashboard", "dashboard");
        addNavButton(side, "▣   Chat Assistant", "chat");
        addNavButton(side, "▦   Class Timetable", "timetable");
        addNavButton(side, "◷   Exam Timetable", "exams");
        addNavButton(side, "▤   Subjects & Faculty", "subjects");

        side.add(Box.createVerticalGlue());

        JLabel classInfo = new JLabel(
            "<html><b>CSE - B SECTION</b><br>" +
            "Classroom: 322<br>" +
            "Strength: 63 students</html>"
        );

        classInfo.setForeground(LIGHT);
        classInfo.setFont(
            new Font("SansSerif", Font.PLAIN, 12)
        );

        classInfo.setBorder(
            new CompoundBorder(
                new LineBorder(
                    new Color(48, 76, 111), 1, true
                ),
                new EmptyBorder(12, 10, 12, 10)
            )
        );

        classInfo.setAlignmentX(Component.LEFT_ALIGNMENT);

        side.add(classInfo);

        return side;
    }

    private void addNavButton(
        JPanel side, String label, String page
    ) {

        JButton button = new JButton(label);

        button.setAlignmentX(Component.LEFT_ALIGNMENT);

        button.setMaximumSize(
            new Dimension(Integer.MAX_VALUE, 43)
        );

        button.setPreferredSize(
            new Dimension(180, 43)
        );

        button.setHorizontalAlignment(
            SwingConstants.LEFT
        );

        button.setFont(
            new Font("SansSerif", Font.BOLD, 13)
        );

        button.setBorder(
            new CompoundBorder(
                new LineBorder(
                    new Color(38, 66, 100), 1, true
                ),
                new EmptyBorder(8, 12, 8, 8)
            )
        );

        addHover(button);

        button.addActionListener(
            e -> showPage(page)
        );

        side.add(button);
        side.add(Box.createVerticalStrut(8));
    }

    // ================= FOOTER =================

    private JPanel createFooter() {

        JPanel footer = new JPanel(
            new FlowLayout(FlowLayout.CENTER)
        );

        footer.setBackground(NAVY);

        JLabel label = new JLabel(
            "SMIT Chatbot Assistant  |  College Student Support System  |  2026"
        );

        label.setForeground(MUTED);

        label.setFont(
            new Font("SansSerif", Font.PLAIN, 11)
        );

        footer.add(label);

        return footer;
    }

    // ================= DASHBOARD =================

    private JPanel createDashboard() {

        JPanel page = pagePanel();

        page.setLayout(
            new BorderLayout(0, 18)
        );

        page.add(
            heading(
                "Welcome to SMIT Assistant",
                "Your college information, timetable and examination helper."
            ),
            BorderLayout.NORTH
        );

        JPanel body = new JPanel(
            new GridLayout(2, 2, 14, 14)
        );

        body.setOpaque(false);

        body.add(
            infoCard(
                "CLASS",
                "II Year - CSE B",
                "Department of Computer Science and Engineering"
            )
        );

        body.add(
            infoCard(
                "CLASSROOM",
                "Room 322",
                "Class in-charge: Mrs. C. Nithya"
            )
        );

        body.add(
            infoCard(
                "STUDENT STRENGTH",
                "63 Students",
                "CSE B Section"
            )
        );

        body.add(
            infoCard(
                "NEXT EXAM",
                nextExamTitle(),
                nextExamCountdown()
            )
        );

        page.add(body, BorderLayout.CENTER);

        JPanel actions = new JPanel(
            new FlowLayout(FlowLayout.LEFT, 8, 0)
        );

        actions.setOpaque(false);

        actions.add(
            actionButton(
                "Open Chat Assistant",
                () -> showPage("chat")
            )
        );

        actions.add(
            actionButton(
                "View Timetable",
                () -> showPage("timetable")
            )
        );

        actions.add(
            actionButton(
                "View Exams",
                () -> showPage("exams")
            )
        );

        page.add(actions, BorderLayout.SOUTH);

        return page;
    }

    private JPanel infoCard(
        String tag, String value, String detail
    ) {

        JPanel card = new JPanel();

        card.setBackground(PANEL);

        card.setLayout(
            new BoxLayout(card, BoxLayout.Y_AXIS)
        );

        card.setBorder(
            new CompoundBorder(
                new LineBorder(
                    new Color(47, 76, 112), 1, true
                ),
                new EmptyBorder(18, 18, 18, 18)
            )
        );

        JLabel t = new JLabel(tag);

        t.setForeground(MUTED);
        t.setFont(
            new Font("SansSerif", Font.BOLD, 11)
        );

        JLabel v = new JLabel(
            "<html>" + value + "</html>"
        );

        v.setForeground(Color.WHITE);
        v.setFont(
            new Font("SansSerif", Font.BOLD, 21)
        );

        JLabel d = new JLabel(
            "<html>" + detail + "</html>"
        );

        d.setForeground(LIGHT);
        d.setFont(
            new Font("SansSerif", Font.PLAIN, 12)
        );

        card.add(t);
        card.add(Box.createVerticalStrut(12));
        card.add(v);
        card.add(Box.createVerticalStrut(8));
        card.add(d);

        return card;
    }

    // ================= EXAM COUNTDOWN =================

    private String nextExamTitle() {

        LocalDate today = LocalDate.now();

        for (String[] exam : exams) {

            LocalDate date = LocalDate.parse(exam[3]);

            if (!date.isBefore(today)) {
                return exam[1];
            }
        }

        return "No upcoming exams";
    }

    private String nextExamCountdown() {

        LocalDate today = LocalDate.now();

        for (String[] exam : exams) {

            LocalDate date = LocalDate.parse(exam[3]);

            if (!date.isBefore(today)) {

                long days = ChronoUnit.DAYS.between(
                    today, date
                );

                return days == 0
                    ? "Scheduled for today (FN)"
                    : days + " day(s) remaining | " +
                      exam[0] + " (FN)";
            }
        }

        return "All listed examinations have passed.";
    }

    // ================= CHAT PAGE =================

    private JPanel createChatPage() {

        JPanel page = pagePanel();

        page.setLayout(
            new BorderLayout(0, 12)
        );

        page.add(
            heading(
                "Chat Assistant",
                "Ask questions about your timetable, exams, subjects and college."
            ),
            BorderLayout.NORTH
        );

        chatMessages = new JPanel();

        chatMessages.setBackground(
            new Color(12, 31, 56)
        );

        chatMessages.setLayout(
            new BoxLayout(
                chatMessages, BoxLayout.Y_AXIS
            )
        );

        chatMessages.setBorder(
            new EmptyBorder(16, 16, 16, 16)
        );

        chatScroll = new JScrollPane(chatMessages);

        chatScroll.setBorder(
            new LineBorder(
                new Color(45, 75, 111), 1, true
            )
        );

        chatScroll.getViewport().setBackground(
            new Color(12, 31, 56)
        );

        page.add(chatScroll, BorderLayout.CENTER);

        JPanel bottom = new JPanel(
            new BorderLayout(8, 8)
        );

        bottom.setOpaque(false);

        JPanel quick = new JPanel(
            new FlowLayout(FlowLayout.LEFT, 6, 0)
        );

        quick.setOpaque(false);

        String[] qs = {
            "Timetable", "Exam Timetable",
            "Day 1", "Day 2", "Day 3",
            "Day 4", "Day 5", "Day 6", "Faculty"
        };

        for (String q : qs) {

            JButton b = smallButton(q);

            b.addActionListener(
                e -> sendQuestion(q)
            );

            quick.add(b);
        }

        JPanel entry = new JPanel(
            new BorderLayout(8, 0)
        );

        entry.setOpaque(false);

        messageField = new JTextField();

        messageField.setFont(
            new Font("SansSerif", Font.PLAIN, 14)
        );

        messageField.setForeground(Color.WHITE);
        messageField.setCaretColor(Color.WHITE);

        messageField.setBackground(
            new Color(20, 43, 72)
        );

        messageField.setBorder(
            new CompoundBorder(
                new LineBorder(
                    new Color(55, 87, 127), 1, true
                ),
                new EmptyBorder(10, 12, 10, 12)
            )
        );

        messageField.addActionListener(
            e -> sendQuestion(messageField.getText())
        );

        JButton send = actionButton(
            "Send",
            () -> sendQuestion(messageField.getText())
        );

        entry.add(messageField, BorderLayout.CENTER);
        entry.add(send, BorderLayout.EAST);

        bottom.add(quick, BorderLayout.NORTH);
        bottom.add(entry, BorderLayout.SOUTH);

        page.add(bottom, BorderLayout.SOUTH);

        addChatBubble(
            "SMIT Assistant",
            "Welcome! Ask me about your class timetable, examination dates, faculty or college details.",
            false
        );

        return page;
    }

    // ================= CHAT MESSAGES =================

    private void addChatBubble(
        String sender, String message, boolean user
    ) {

        if (chatMessages == null) return;

        JPanel row = new JPanel(
            new FlowLayout(
                user ? FlowLayout.RIGHT : FlowLayout.LEFT,
                0, 7
            )
        );

        row.setOpaque(false);

        JPanel bubble = new JPanel();

        bubble.setLayout(
            new BoxLayout(
                bubble, BoxLayout.Y_AXIS
            )
        );

        bubble.setBackground(
            user
                ? new Color(54, 91, 160)
                : new Color(28, 56, 88)
        );

        bubble.setBorder(
            new CompoundBorder(
                new LineBorder(
                    user
                        ? new Color(83, 125, 199)
                        : new Color(48, 81, 119),
                    1, true
                ),
                new EmptyBorder(10, 13, 10, 13)
            )
        );

        JLabel name = new JLabel(sender);

        name.setForeground(
            user
                ? new Color(220, 232, 255)
                : new Color(130, 207, 255)
        );

        name.setFont(
            new Font("SansSerif", Font.BOLD, 11)
        );

        JTextArea body = new JTextArea(message);

        body.setEditable(false);
        body.setLineWrap(true);
        body.setWrapStyleWord(true);
        body.setOpaque(false);

        body.setForeground(Color.WHITE);

        body.setFont(
            new Font("SansSerif", Font.PLAIN, 13)
        );

        body.setColumns(48);

        body.setRows(
            Math.max(
                1,
                Math.min(16, message.split("\n").length)
            )
        );

        bubble.add(name);
        bubble.add(Box.createVerticalStrut(5));
        bubble.add(body);

        row.add(bubble);

        chatMessages.add(row);

        chatMessages.revalidate();
        chatMessages.repaint();

        SwingUtilities.invokeLater(() -> {

            JScrollBar bar =
                chatScroll.getVerticalScrollBar();

            bar.setValue(bar.getMaximum());
        });
    }

    // ================= CLASS TIMETABLE =================

    private JPanel createTimetablePage() {

        JPanel page = pagePanel();

        page.setLayout(
            new BorderLayout(0, 14)
        );

        page.add(
            heading(
                "Class Timetable",
                "CSE - B Section | Day 1 to Day 6"
            ),
            BorderLayout.NORTH
        );

        String[] columns = {
            "Day",
            "9:00-9:50",
            "9:50-10:40",
            "10:55-11:45",
            "11:45-12:30",
            "1:15-2:00",
            "2:00-2:45",
            "2:45-3:30"
        };

        Object[][] rows = {

            {"Day 1", "OOSE", "OS", "JAVA", "DS", "DM", "DM", "Library"},

            {"Day 2", "JAVA", "OOSE", "DS", "OS",
             "Skill Dev. Lab*", "Skill Dev. Lab*", "NPTEL"},

            {"Day 3", "OS", "DM", "JAVA", "DS",
             "DS LAB*", "DS LAB*", "DS LAB*"},

            {"Day 4", "DM", "OS", "OOSE", "DS",
             "JAVA LAB*", "JAVA LAB*", "JAVA LAB*"},

            {"Day 5", "DS", "JAVA", "DM", "Placement",
             "OOSE", "English Lab*", "English Lab*"},

            {"Day 6", "DM", "OOSE", "OS LAB*", "OS LAB*",
             "JAVA/Seminar", "OS", "PET"}
        };

        JTable table = new JTable(rows, columns);

        styleTable(table);

        JScrollPane scroll = new JScrollPane(table);

        scroll.getViewport().setBackground(PANEL);

        scroll.setBorder(
            new LineBorder(
                new Color(48, 76, 111), 1, true
            )
        );

        JLabel note = new JLabel(
            "<html>* Lab blocks span multiple periods. " +
            "Break: 10:40-10:55 | Lunch: 12:30-1:15. " +
            "Day labels are retained from the supplied timetable.</html>"
        );

        note.setForeground(MUTED);

        note.setBorder(
            new EmptyBorder(8, 2, 0, 2)
        );

        JPanel center = new JPanel(
            new BorderLayout(0, 8)
        );

        center.setOpaque(false);

        center.add(scroll, BorderLayout.CENTER);
        center.add(note, BorderLayout.SOUTH);

        page.add(center, BorderLayout.CENTER);

        return page;
    }

    // ================= EXAM TIMETABLE =================

    private JPanel createExamPage() {

        JPanel page = pagePanel();

        page.setLayout(
            new BorderLayout(0, 14)
        );

        page.add(
            heading(
                "End Semester Examination",
                "Session: Forenoon (FN)"
            ),
            BorderLayout.NORTH
        );

        String[] columns = {
            "Exam Date",
            "Session",
            "Subject Code",
            "Subject Name",
            "Countdown"
        };

        Object[][] rows = new Object[exams.length][5];

        for (int i = 0; i < exams.length; i++) {

            rows[i][0] = exams[i][0];
            rows[i][1] = "FN";
            rows[i][2] = exams[i][2];
            rows[i][3] = exams[i][1];

            LocalDate date =
                LocalDate.parse(exams[i][3]);

            long days = ChronoUnit.DAYS.between(
                LocalDate.now(), date
            );

            rows[i][4] = days < 0
                ? "Completed"
                : (days == 0
                    ? "Today"
                    : days + " days");
        }

        JTable table = new JTable(rows, columns);

        styleTable(table);

        table.getColumnModel()
             .getColumn(3)
             .setPreferredWidth(300);

        JScrollPane scroll = new JScrollPane(table);

        scroll.getViewport().setBackground(PANEL);

        scroll.setBorder(
            new LineBorder(
                new Color(48, 76, 111), 1, true
            )
        );

        page.add(scroll, BorderLayout.CENTER);

        JLabel note = new JLabel(
            "Exam dates and FN session are transcribed from the examination timetable provided. Exact exam start time was not shown."
        );

        note.setForeground(MUTED);

        page.add(note, BorderLayout.SOUTH);

        return page;
    }

    // ================= SUBJECTS & FACULTY =================

    private JPanel createSubjectsPage() {

        JPanel page = pagePanel();

        page.setLayout(
            new BorderLayout(0, 14)
        );

        page.add(
            heading(
                "Subjects & Faculty",
                "CSE - B Section"
            ),
            BorderLayout.NORTH
        );

        String[] columns = {
            "Course Code",
            "Course Title",
            "Faculty"
        };

        Object[][] rows = {

            {"MA25C08", "Discrete Mathematics", "Mrs. T. Monica"},

            {"CS25C11", "Operating Systems", "Mrs. S. Kavitha"},

            {"CS25C10", "Object Oriented Software Engineering",
             "Mrs. R. Malarvizhi"},

            {"CS25C08", "Data Structures", "Mrs. T. Saranya"},

            {"CS25C09", "Java Programming", "Mrs. M. Priyanka"}
        };

        JTable table = new JTable(rows, columns);

        styleTable(table);

        table.getColumnModel()
             .getColumn(1)
             .setPreferredWidth(330);

        JScrollPane scroll = new JScrollPane(table);

        scroll.getViewport().setBackground(PANEL);

        scroll.setBorder(
            new LineBorder(
                new Color(48, 76, 111), 1, true
            )
        );

        page.add(scroll, BorderLayout.CENTER);

        JLabel note = new JLabel(
            "Class in-charge: Mrs. C. Nithya  |  Classroom: 322  |  Student strength: 62"
        );

        note.setForeground(MUTED);

        page.add(note, BorderLayout.SOUTH);

        return page;
    }

    // ================= TABLE DESIGN =================

    private void styleTable(JTable table) {

        table.setRowHeight(42);

        table.setFont(
            new Font("SansSerif", Font.PLAIN, 13)
        );

        table.setForeground(LIGHT);
        table.setBackground(PANEL);

        table.setGridColor(
            new Color(54, 82, 116)
        );

        table.setSelectionBackground(
            new Color(55, 91, 145)
        );

        table.setSelectionForeground(Color.WHITE);

        table.setFillsViewportHeight(true);

        table.getTableHeader().setFont(
            new Font("SansSerif", Font.BOLD, 12)
        );

        table.getTableHeader().setForeground(
            Color.WHITE
        );

        table.getTableHeader().setBackground(
            new Color(39, 76, 121)
        );

        table.getTableHeader().setReorderingAllowed(false);
    }

    // ================= PAGE DESIGN =================

    private JPanel pagePanel() {

        JPanel p = new JPanel();

        p.setBackground(NAVY);

        return p;
    }

    private JPanel heading(
        String titleText, String subtitleText
    ) {

        JPanel p = new JPanel();

        p.setOpaque(false);

        p.setLayout(
            new BoxLayout(p, BoxLayout.Y_AXIS)
        );

        JLabel title = new JLabel(titleText);

        title.setForeground(Color.WHITE);

        title.setFont(
            new Font("SansSerif", Font.BOLD, 24)
        );

        JLabel subtitle = new JLabel(subtitleText);

        subtitle.setForeground(MUTED);

        subtitle.setFont(
            new Font("SansSerif", Font.PLAIN, 13)
        );

        p.add(title);
        p.add(Box.createVerticalStrut(5));
        p.add(subtitle);

        return p;
    }

    // ================= FIXED BUTTON COLOR =================

    private void addHover(JButton button) {

        // Force Basic Swing UI to prevent Windows grey buttons
        button.setUI(new BasicButtonUI());

        button.setOpaque(true);
        button.setContentAreaFilled(true);
        button.setBorderPainted(true);
        button.setFocusPainted(false);

        button.setBackground(BUTTON_BLUE);
        button.setForeground(Color.WHITE);

        button.setCursor(
            Cursor.getPredefinedCursor(
                Cursor.HAND_CURSOR
            )
        );

        button.addMouseListener(
            new java.awt.event.MouseAdapter() {

                @Override
                public void mouseEntered(
                    java.awt.event.MouseEvent e
                ) {

                    button.setBackground(BUTTON_HOVER);
                }

                @Override
                public void mouseExited(
                    java.awt.event.MouseEvent e
                ) {

                    button.setBackground(BUTTON_BLUE);
                }
            }
        );
    }

    // ================= ACTION BUTTON =================

    private JButton actionButton(
        String text, Runnable action
    ) {

        JButton b = new JButton(text);

        b.setFont(
            new Font("SansSerif", Font.BOLD, 12)
        );

        b.setBorder(
            new CompoundBorder(
                new LineBorder(
                    new Color(55, 85, 125), 1, true
                ),
                new EmptyBorder(10, 16, 10, 16)
            )
        );

        addHover(b);

        b.addActionListener(
            e -> action.run()
        );

        return b;
    }

    // ================= QUICK BUTTON =================

    private JButton smallButton(String text) {

        JButton b = new JButton(text);

        b.setFont(
            new Font("SansSerif", Font.BOLD, 11)
        );

        b.setBorder(
            new CompoundBorder(
                new LineBorder(
                    new Color(55, 85, 125), 1, true
                ),
                new EmptyBorder(7, 12, 7, 12)
            )
        );

        addHover(b);

        return b;
    }

    // ================= NAVIGATION =================

    private void showPage(String page) {

        cardLayout.show(contentCards, page);

        if ("chat".equals(page)
            && chatMessages.getComponentCount() == 1) {

            addChatBubble(
                "SMIT Assistant",
                "You can ask: timetable, exam timetable, Day 1, Java exam date, faculty details, classroom number or help.",
                false
            );
        }
    }

    // ================= SEND QUESTION =================

    private void sendQuestion(String question) {

        if (question == null ||
            question.trim().isEmpty()) {
            return;
        }

        String clean = question.trim();

        addChatBubble(
            "You", clean, true
        );

        addChatBubble(
            "SMIT Assistant",
            getResponse(clean),
            false
        );

        messageField.setText("");
    }

    // ================= DAY DETECTION =================

    private boolean isDay(String q, int day) {

        String[] words = {
            "", "one", "two", "three",
            "four", "five", "six"
        };

        String n = String.valueOf(day);

        String regex =
            ".*\\b(day\\s*" + n +
            "|" + n + "(st|nd|rd|th)?\\s*day" +
            "|day\\s*" + words[day] +
            "|" + words[day] + "\\s*day)\\b.*";

        return q.matches(regex);
    }

    // ================= CHATBOT RESPONSE =================

    private String getResponse(String message) {

        String q = message.toLowerCase().trim();

        if (q.matches(".*\\b(hi|hello|hey)\\b.*")) {

            return "Hello! Welcome to SMIT Chatbot Assistant. How can I help you?";
        }

        if (q.equals("help") ||
            q.contains("what can you do")) {

            return "I can help with class timetable, exam timetable, subject codes, faculty names, classroom 322, class in-charge, attendance, library and college details.";
        }

        if (q.matches(".*\\b(bye|goodbye)\\b.*")) {

            return "Thank you for using SMIT Chatbot Assistant. Have a great day!";
        }

        if ((q.contains("exam") || q.contains("examination"))
            && q.contains("java")) {

            return "Java Programming (CS25C09): 30 November 2026, Monday - Forenoon (FN).";
        }

        if ((q.contains("exam") || q.contains("examination"))
            && (q.contains("data structure") ||
                q.matches(".*\\bds\\b.*"))) {

            return "Data Structures (CS25C08): 27 November 2026, Friday - Forenoon (FN).";
        }

        if ((q.contains("exam") || q.contains("examination"))
            && (q.contains("discrete") ||
                q.matches(".*\\bdm\\b.*"))) {

            return "Discrete Mathematics (MA25C08): 25 November 2026, Wednesday - Forenoon (FN).";
        }

        if ((q.contains("exam") || q.contains("examination"))
            && (q.contains("operating") ||
                q.matches(".*\\bos\\b.*"))) {

            return "Operating Systems (CS25C11): 04 December 2026, Friday - Forenoon (FN).";
        }

        if ((q.contains("exam") || q.contains("examination"))
            && (q.contains("software engineering") ||
                q.contains("oose"))) {

            return "Object Oriented Software Engineering (CS25C10): 02 December 2026, Wednesday - Forenoon (FN).";
        }

        if (q.contains("exam") ||
            q.contains("examination")) {

            return "SMIT END SEMESTER EXAMINATION\n" +
                "SESSION: FORENOON (FN)\n\n" +
                "25-11-2026 - Discrete Mathematics (MA25C08)\n" +
                "27-11-2026 - Data Structures (CS25C08)\n" +
                "30-11-2026 - Java Programming (CS25C09)\n" +
                "02-12-2026 - Object Oriented Software Engineering (CS25C10)\n" +
                "04-12-2026 - Operating Systems (CS25C11)\n\n" +
                "All the best for your exams!";
        }

        if (isDay(q, 1)) {

            return "DAY 1 - CSE B SECTION\n" +
                "09:00-09:50 OOSE\n" +
                "09:50-10:40 OS\n" +
                "10:40-10:55 BREAK\n" +
                "10:55-11:45 JAVA\n" +
                "11:45-12:30 DS\n" +
                "12:30-01:15 LUNCH\n" +
                "01:15-02:00 DM\n" +
                "02:00-02:45 DM\n" +
                "02:45-03:30 LIBRARY";
        }

        if (isDay(q, 2)) {

            return "DAY 2 - CSE B SECTION\n" +
                "09:00-09:50 JAVA\n" +
                "09:50-10:40 OOSE\n" +
                "10:40-10:55 BREAK\n" +
                "10:55-11:45 DS\n" +
                "11:45-12:30 OS\n" +
                "12:30-01:15 LUNCH\n" +
                "01:15-02:45 SKILL DEVELOPMENT LAB\n" +
                "02:45-03:30 NPTEL";
        }

        if (isDay(q, 3)) {

            return "DAY 3 - CSE B SECTION\n" +
                "09:00-09:50 OS\n" +
                "09:50-10:40 DM\n" +
                "10:40-10:55 BREAK\n" +
                "10:55-11:45 JAVA\n" +
                "11:45-12:30 DS\n" +
                "12:30-01:15 LUNCH\n" +
                "01:15-03:30 DS LAB";
        }

        if (isDay(q, 4)) {

            return "DAY 4 - CSE B SECTION\n" +
                "09:00-09:50 DM\n" +
                "09:50-10:40 OS\n" +
                "10:40-10:55 BREAK\n" +
                "10:55-11:45 OOSE\n" +
                "11:45-12:30 DS\n" +
                "12:30-01:15 LUNCH\n" +
                "01:15-03:30 JAVA LAB";
        }

        if (isDay(q, 5)) {

            return "DAY 5 - CSE B SECTION\n" +
                "09:00-09:50 DS\n" +
                "09:50-10:40 JAVA\n" +
                "10:40-10:55 BREAK\n" +
                "10:55-11:45 DM\n" +
                "11:45-12:30 PLACEMENT\n" +
                "12:30-01:15 LUNCH\n" +
                "01:15-02:00 OOSE\n" +
                "02:00-03:30 ENGLISH COMMUNICATION SKILLS LAB I";
        }

        if (isDay(q, 6)) {

            return "DAY 6 - CSE B SECTION\n" +
                "09:00-09:50 DM\n" +
                "09:50-10:40 OOSE\n" +
                "10:40-10:55 BREAK\n" +
                "10:55-12:30 OS LAB\n" +
                "12:30-01:15 LUNCH\n" +
                "01:15-02:00 JAVA / SEMINAR / MMP\n" +
                "02:00-02:45 OS\n" +
                "02:45-03:30 PET";
        }

        if (q.contains("timetable") ||
            q.contains("time table") ||
            q.contains("schedule")) {

            return "CSE B SECTION TIMETABLE\n" +
                "Day 1: OOSE, OS, JAVA, DS, DM, Library\n" +
                "Day 2: JAVA, OOSE, DS, OS, Skill Development Lab, NPTEL\n" +
                "Day 3: OS, DM, JAVA, DS, DS Lab\n" +
                "Day 4: DM, OS, OOSE, DS, Java Lab\n" +
                "Day 5: DS, JAVA, DM, Placement, OOSE, English Lab\n" +
                "Day 6: DM, OOSE, OS Lab, JAVA/Seminar/MMP, OS, PET\n" +
                "Ask for a specific day for full details.";
        }

        if (q.contains("faculty") ||
            q.contains("teacher") ||
            q.contains("staff")) {

            return "FACULTY DETAILS\n" +
                "Discrete Mathematics: Mrs. T. Monica\n" +
                "Operating Systems: Mrs. S. Kavitha\n" +
                "OOSE: Mrs. R. Malarvizhi\n" +
                "Data Structures: Mrs. T. Saranya\n" +
                "Java Programming: Mrs. M. Priyanka\n" +
                "Class In-charge: Mrs. C. Nithya";
        }

        if (q.contains("subject") ||
            q.contains("course")) {

            return "MAIN SUBJECTS\n" +
                "MA25C08 - Discrete Mathematics\n" +
                "CS25C11 - Operating Systems\n" +
                "CS25C10 - Object Oriented Software Engineering\n" +
                "CS25C08 - Data Structures\n" +
                "CS25C09 - Java Programming\n\n" +
                "Labs: OS Lab, DS Lab, Java Lab.";
        }

        if (q.contains("incharge") ||
            q.contains("class teacher")) {

            return "Your CSE B Section class in-charge is Mrs. C. Nithya.";
        }

        if (q.contains("classroom") ||
            q.contains("room number")) {

            return "Your CSE B Section classroom is 322.";
        }

        if (q.contains("students") ||
            q.contains("strength")) {

            return "Your CSE B Section has 63 students.";
        }

        if (q.contains("college") ||
            q.contains("department")) {

            return "Sri Muthukumaran Institute of Technology\n" +
                "Department: Computer Science and Engineering\n" +
                "Section: B | Year: II Year\n" +
                "Semester: Odd Semester\n" +
                "Classroom: 322\n" +
                "Student strength: 62\n" +
                "Class in-charge: Mrs. C. Nithya";
        }

        if (q.contains("attendance")) {

            return "Please check your official college regulations or contact your class in-charge for the minimum attendance requirement.";
        }

        if (q.contains("library")) {

            return "Library appears in the timetable on Day 1 from 2:45 PM to 3:30 PM. Contact library staff for general opening hours.";
        }

        if (q.contains("placement")) {

            return "Placement is scheduled on Day 5 from 11:45 AM to 12:30 PM.";
        }

        if (q.contains("break")) {

            return "Morning break: 10:40 AM - 10:55 AM.";
        }

        if (q.contains("lunch")) {

            return "Lunch break: 12:30 PM - 01:15 PM.";
        }

        return "Sorry, I couldn't find an answer. Try 'help', 'timetable', 'exam timetable', 'Day 1', 'faculty' or 'subjects'.";
    }

    // ================= MAIN METHOD =================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            try {

                UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName()
                );

            } catch (Exception ignored) {
            }

            new Main().setVisible(true);
        });
    }
}
