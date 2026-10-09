import { notFound } from "next/navigation";
import Navbar from "@/components/Navbar";
import { getArticleBySlug } from "@/lib/api";

const categoryLabel: Record<string, string> = {
  FICCION: "Ficción",
  CONSPIRACION: "Conspiración",
  TERROR: "Terror",
  MISTERIO: "Misterio",
  OTROS: "Otros",
};

export default async function ArticlePage({
  params,
}: {
  params: { slug: string };
}) {
  const article = await getArticleBySlug(params.slug);
  if (!article) notFound();

  return (
    <>
      <Navbar />
      <main className="mx-auto max-w-2xl px-6 py-12">
        <span className="w-fit rounded-full bg-brand-purple/20 px-3 py-1 text-xs text-brand-lilac">
          {categoryLabel[article.category] ?? article.category}
        </span>
        <h1 className="mt-4 text-3xl font-bold">{article.title}</h1>
        <p className="mt-2 text-sm text-brand-light/50">
          {new Date(article.publishedAt).toLocaleDateString("es-AR", {
            day: "numeric",
            month: "long",
            year: "numeric",
          })}{" "}
          · {article.views} lecturas
        </p>

        {article.imageUrl && (
          // eslint-disable-next-line @next/next/no-img-element
          <img
            src={article.imageUrl}
            alt={article.title}
            className="my-8 w-full rounded-xl object-cover"
          />
        )}

        <article className="prose prose-invert max-w-none whitespace-pre-line text-brand-light/90">
          {article.content}
        </article>
      </main>
    </>
  );
}